package org.tcsaroundtheworld.common.shared;


public interface IsPerson {

	void accept(PersonVisitor visitor);

	PersonVisitor createCopyVisitor();

}
