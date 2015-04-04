package org.tcsaroundtheworld.common.server.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tcsaroundtheworld.admin.shared.FamilyFull;
import org.tcsaroundtheworld.admin.shared.PersonFull;
import org.tcsaroundtheworld.common.server.Picture;
import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.common.shared.PersonPublic;
import org.tcsaroundtheworld.common.shared.PictureReference;
import org.tcsaroundtheworld.map.server.LocationStatsCollector;
import org.tcsaroundtheworld.map.shared.LocationStats;
import org.tcsaroundtheworld.submit.server.SubmissionServiceImpl;
import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.PersonSubmission;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class DAO {

	Logger log = Logger.getLogger(DAO.class.getName());

	static {
		ObjectifyService.register(PersonEntity.class);
		ObjectifyService.register(FamilyEntity.class);
		ObjectifyService.register(PictureEntity.class);
	}

	public void storeFamily(final FamilySubmission f) {
		Objectify ofy = ObjectifyService.beginTransaction();
		final List<Objectify> ofyList = new ArrayList<Objectify>();
		try {

			final FamilyEntity fe = new FamilyEntity();
			f.copyTo(fe);
			final Key<FamilyEntity> familyKey = ofy.put(fe);

			for( final PersonSubmission p : f.getMembers() ) {
				log.info("Storing "+p.toString());

				final PersonEntity pe = new PersonEntity(familyKey);
				pe.accept( p.createCopyVisitor() );
				ofy.put(pe);
				final PictureReference pic = p.getBase().getPictureReference();
				if( pic != null ) {
					Objectify ofyForPicCheck = ObjectifyService.begin();
					if( ofyForPicCheck.query(PictureEntity.class).filter("id =", pic.getId()).fetchKeys().iterator().hasNext() ) {
						log.info("Picture already exists, must be a re-import, not persisting image with id="+pic.getId());
					} else {
						final PictureEntity pictureEntity = PictureEntity.fromMemCache(pic);
						log.info("Storing "+pictureEntity.toString());
						final Objectify ofyForPic = ObjectifyService.beginTransaction();
						ofyList.add( ofyForPic );
						ofyForPic.put( pictureEntity );
					}
				}
			}
			ofy.getTxn().commit();
			for( final Objectify ofy2 : ofyList ) {
				ofy2.getTxn().commit();
			}
		} catch( final Exception e ) {
			log.log(Level.SEVERE, "Failed to store family "+e.getMessage(), e);
			throw new RuntimeException("Failed to store family: "+e.getMessage(), e);
		} finally {
			if( ofy.getTxn().isActive()) {
				ofy.getTxn().rollback();
			}
			for( final Objectify ofy2 : ofyList ) {
				if( ofy2.getTxn().isActive()) {
					ofy2.getTxn().rollback();
				}
			}
		}
	}

	public LocationStats getCountryStats() {
		Objectify ofy = ObjectifyService.begin();
		final LocationStatsCollector statsCollector = new LocationStatsCollector();
		for( final FamilyEntity f : ofy.query(FamilyEntity.class).filter("approved =", true) ) {
			for( final PersonEntity p : ofy.query(PersonEntity.class).ancestor(f).filter("enabled =", true) ) {
				statsCollector.incrementCountry(p.country);
				if( p.country != null && ( p.country.equals("US") || p.country.equals("USA") || p.country.equals("United States") ) ) {
					statsCollector.incrementUsCounty(p.state);
				}
			}
		}
		return statsCollector.toLocationStats();
	}

	public List<Family> getFamiliesForPublic() {
		if( !SubmissionServiceImpl.isProdMode() ) {
			try {
				Thread.sleep(19000);
			} catch (InterruptedException e) {
				//NOOP
			}
		}
		long startTime = System.currentTimeMillis();
		Objectify ofy = ObjectifyService.begin();
		final List<Family> families = new ArrayList<Family>();
		QueryResultIterator<FamilyEntity> familyIterator = ofy.query(FamilyEntity.class).filter("approved =", true).iterator();
		while( familyIterator.hasNext() ) {
			FamilyEntity f = familyIterator.next();
			Family family = new Family(f);
			final List<PersonPublic> members = new ArrayList<PersonPublic>();
			QueryResultIterator<PersonEntity> personIterator = ofy.query(PersonEntity.class).ancestor(f).filter("enabled =", true).iterator();
			while( personIterator.hasNext() ) {
				PersonEntity p = personIterator.next();
				final PersonPublic pb = new PersonPublic();
				pb.accept( p.createCopyVisitor() );
				members.add( pb );
			}
			family.setMembers(members);
			families.add( family );
		}
		log.info("getFamiliesForPublic mod taken "+(System.currentTimeMillis()-startTime)+"ms");
		return families;
	}

	public List<FamilyFull> getFamiliesForAdmin() {
		Objectify ofy = ObjectifyService.begin();
		final List<FamilyFull> families = new ArrayList<FamilyFull>();
		for( final FamilyEntity f : ofy.query(FamilyEntity.class) ) {
			final FamilyFull family = new FamilyFull( f );
			final List<PersonFull> members = new ArrayList<PersonFull>();
			for( final PersonEntity p : ofy.query(PersonEntity.class).ancestor(f) ) {
				final PersonFull pf = new PersonFull();
				pf.accept( p.createCopyVisitor() );
				members.add( pf );
			}
			family.setMembersFull(members);
			families.add( family );
		}
		return families;
	}

	public String getPersonEmail(final Long familyId, final Long personId  ) {
		Objectify ofy = ObjectifyService.begin();
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, familyId);
		final FamilyEntity f = ofy.get(familyKey);
		if( f == null || !f.approved  ) {
			return null;
		}
		final Key<PersonEntity> personKey = new Key<PersonEntity>(familyKey, PersonEntity.class, personId);
		final PersonEntity p = ofy.get(personKey);
		if( p == null || !p.enabled || !p.contactable ) {
			return null;
		}
		return p.email;
	}

	public Picture getPicture(final String ref) {
		Objectify ofy = ObjectifyService.begin();
		final PictureEntity pictureEntity = ofy.get( PictureEntity.class, ref );
		return pictureEntity.toPicture();
	}

	public void enablePerson(final long familyId, final long id, final Boolean value) {
		Objectify ofy = ObjectifyService.begin();
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, familyId);
		final Key<PersonEntity> personKey = new Key<PersonEntity>(familyKey, PersonEntity.class, id);
		final PersonEntity p = ofy.get(personKey);
		p.enabled = value;
		ofy.put( p );
	}

	public void approveFamily(final long id) {
		Objectify ofy = ObjectifyService.begin();
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, id);
		final FamilyEntity f = ofy.get(familyKey);
		f.approved = true;
		ofy.put( f );
	}

	public void deletePerson(final long id) {
		Objectify ofy = ObjectifyService.begin();
		ofy.delete(PersonEntity.class, id);
	}

	public void deleteFamily(final long id) {
		Objectify ofy = ObjectifyService.begin();
		final FamilyEntity f = ofy.get(FamilyEntity.class,id);
		ofy.delete( ofy.query(PersonEntity.class).ancestor(f) );
		ofy.delete(f);
	}

	public int familiesAwaitingApproval() {
		Objectify ofy = ObjectifyService.begin();
		return ofy.query(FamilyEntity.class).filter("approved = ", false).count();
	}

}
