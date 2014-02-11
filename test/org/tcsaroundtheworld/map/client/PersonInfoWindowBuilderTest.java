package org.tcsaroundtheworld.map.client;

import org.tcsaroundtheworld.common.client.DateParserClient;
import org.tcsaroundtheworld.common.shared.PersonBase;
import org.tcsaroundtheworld.common.shared.PersonId;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonInfoWindowBuilderTest extends GWTTestCase {

	public void testBuild() {
		assertNotNull( builder().build(createPersonBase(),createPersonId()));
	}

	public void testBuildWithNullDob() {
		final PersonBase p = createPersonBase();
		p.setDob(null);
		assertNotNull( builder().build(p,createPersonId()) );
	}

	public void testBuildWithNullOccupation() {
		final PersonBase p = createPersonBase();
		p.setOccupation(null);
		assertNotNull( builder().build(p,createPersonId()) );
	}

	public void testBuildWithNullOperations() {
		final PersonBase p = createPersonBase();
		p.setOperations(null);
		assertNotNull( builder().build(p,createPersonId()) );
	}

	public PersonInfoWindowBuilder builder() {
		return new PersonInfoWindowBuilder();
	}

	public PersonBase createPersonBase() {
		final PersonBase person = new PersonBase();
		person.setName("Jo");
		person.setSurname("Bloggs");
		person.setGender("Male");
		person.setInheritance("Sporadic");
		person.setCity("london");
		person.setState("london");
		person.setCountry("UK");
		person.setLatitude("1.1");
		person.setLongitude("1.1");
		person.setDob("01/01/2000");
		person.setOccupation("Student");
		person.setOperations("1");
		return person;
	}

	public PersonId createPersonId() {
		final PersonId id = new PersonId();
		id.setFamilyId(1);
		id.setId(2);
		return id;
	}

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.map.Map";
	}

}
