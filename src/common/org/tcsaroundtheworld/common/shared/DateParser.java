package org.tcsaroundtheworld.common.shared;

import java.util.Date;

public interface DateParser {
	Date getDate();
	int getMonthsTill(Date tillDate);
	int getYearsTill(Date tillDate);
	int getDaysTill(Date tillDate);

}
