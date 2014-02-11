package org.tcsaroundtheworld.common.shared;

public interface IsPersonComponent {
	void accept( PersonVisitor visitor );
}
