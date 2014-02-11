package org.tcsaroundtheworld.common.shared;

public interface PersonVisitor {
	void visit( HasPersonBase person );
	void visit( HasPersonId person );
	void visit( HasPersonEmail person );
	void visit( HasPersonEnabled person );
}
