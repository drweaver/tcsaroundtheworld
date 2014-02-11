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
import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.PersonSubmission;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.helper.DAOBase;

public class DAO extends DAOBase {

	Logger log = Logger.getLogger(DAO.class.getName());

	static {
		ObjectifyService.register(PersonEntity.class);
		ObjectifyService.register(FamilyEntity.class);
		ObjectifyService.register(PictureEntity.class);
	}

	public void storeFamily(final FamilySubmission f) {
		final Objectify ofy = fact().beginTransaction();
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
					if( ofy().query(PictureEntity.class).filter("id =", pic.getId()).fetchKeys().iterator().hasNext() ) {
						log.info("Picture already exists, must be a re-import, not persisting image with id="+pic.getId());
					} else {
						final PictureEntity pictureEntity = PictureEntity.fromMemCache(pic);
						log.info("Storing "+pictureEntity.toString());
						final Objectify ofyForPic = fact().beginTransaction();
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
		final LocationStatsCollector statsCollector = new LocationStatsCollector();

		for( final FamilyEntity f : ofy().query(FamilyEntity.class).filter("approved =", true) ) {
			for( final PersonEntity p : ofy().query(PersonEntity.class).ancestor(f).filter("enabled =", true) ) {
				statsCollector.incrementCountry(p.country);
				if( p.country != null && ( p.country.equals("US") || p.country.equals("USA") || p.country.equals("United States") ) ) {
					statsCollector.incrementUsCounty(p.state);
				}
			}
		}
		return statsCollector.toLocationStats();
	}

	public List<Family> getFamiliesForPublic() {
		final List<Family> families = new ArrayList<Family>();
		for( final FamilyEntity f : ofy().query(FamilyEntity.class).filter("approved =", true) ) {
			final Family family = new Family(f);
			final List<PersonPublic> members = new ArrayList<PersonPublic>();
			for( final PersonEntity p : ofy().query(PersonEntity.class).ancestor(f).filter("enabled =", true) ) {
				final PersonPublic pb = new PersonPublic();
				pb.accept( p.createCopyVisitor() );
				members.add( pb );
			}
			family.setMembers(members);
			families.add( family );
		}
		return families;
	}

	public List<FamilyFull> getFamiliesForAdmin() {
		final List<FamilyFull> families = new ArrayList<FamilyFull>();
		for( final FamilyEntity f : ofy().query(FamilyEntity.class) ) {
			final FamilyFull family = new FamilyFull( f );
			final List<PersonFull> members = new ArrayList<PersonFull>();
			for( final PersonEntity p : ofy().query(PersonEntity.class).ancestor(f) ) {
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
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, familyId);
		final FamilyEntity f = ofy().get(familyKey);
		if( f == null || !f.approved  ) {
			return null;
		}
		final Key<PersonEntity> personKey = new Key<PersonEntity>(familyKey, PersonEntity.class, personId);
		final PersonEntity p = ofy().get(personKey);
		if( p == null || !p.enabled || !p.contactable ) {
			return null;
		}
		return p.email;
	}

	public Picture getPicture(final String ref) {
		final PictureEntity pictureEntity = ofy().get( PictureEntity.class, ref );
		return pictureEntity.toPicture();
	}

	public void enablePerson(final long familyId, final long id, final Boolean value) {
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, familyId);
		final Key<PersonEntity> personKey = new Key<PersonEntity>(familyKey, PersonEntity.class, id);
		final PersonEntity p = ofy().get(personKey);
		p.enabled = value;
		ofy().put( p );
	}

	public void approveFamily(final long id) {
		final Key<FamilyEntity> familyKey = new Key<FamilyEntity>(FamilyEntity.class, id);
		final FamilyEntity f = ofy().get(familyKey);
		f.approved = true;
		ofy().put( f );
	}

	public void deletePerson(final long id) {
		ofy().delete(PersonEntity.class, id);
	}

	public void deleteFamily(final long id) {
		final FamilyEntity f = ofy().get(FamilyEntity.class,id);
		ofy().delete( ofy().query(PersonEntity.class).ancestor(f) );
		ofy().delete(f);
	}

	public int familiesAwaitingApproval() {
		return ofy().query(FamilyEntity.class).filter("approved = ", false).count();
	}

}
