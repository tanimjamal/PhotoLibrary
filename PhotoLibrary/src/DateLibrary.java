
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

public class DateLibrary {

	public static boolean isValidDateFormat(
			String date) { // method used to check if the date is correctly formated in "YYYY-MM-DD" form
		return date.matches("\\d{4}-\\d{2}-\\d{2}"); /*  return a boolean based off if the String contains 4 digits
												      * followed by a hyphen followed by 2 more digits followed by
													  * another hyphen and 2 more digits */
	}

	public static int getYear(String date) { // method used to obtain the year from the String date
		if (isValidDateFormat(date)) { // check to see if the date is in a valid format
			int year = Integer.parseInt(date.substring(0, 4)); // use ParseInt to get an integer value from a substring of the date
			return year; // return the year value
		} else {
			return -1; // if date format is invalid return -1
		}
	}

	public static int getMonth(String date) { // method used to obtain the month from the String date
		if (isValidDateFormat(date)) { // check to see if the date is in a valid format
			int month = Integer.parseInt(date.substring(5, 7)); // use ParseInt to get an integer value from a substring of the date
			if (month >= 1 && month <= 12) { // if the month value is valid return it
				return month;
			} else {
				return -1; // if month value is invalid then return -1
			}
		}
		return -1; // if date format is invalid return -1
	}

	public static int getDay(String date) { // method used to obtain the day from the String date
		if (isValidDateFormat(date)) { // check to see if the date is in a valid format
			int day = Integer.parseInt(date.substring(8, 10)); // use ParseInt to get an integer value from a substring of the date
			if (day >= 1 && day <= 31) { // if the day value is valid return it
				return day;
			} else {
				return -1; // if the day valid is invalid return -1
			}
		}
		return -1; // if date format is invalid return -1

	}

	public static boolean isLeapYear(int year) { // method used to check if a given year is a leap year
		boolean valid = false;
		if (((year % 4 == 0) && (year % 100 != 0))
				|| (year % 400 == 0)) { /* check to see if year is divisible by 4 and not divisible by 100 if so then it
										 * is a leap year, or if it is divisible by 400 then it is a leap year as well*/
			valid = true; // change the boolean to true if it is a leap year
		}
		return valid; // return the boolean value
	}

	public static boolean isValidDate(String date) { // method used to check if the date is an actual date
		if (isValidDateFormat(date) && getDay(date) != -1) { // check if it is in the correct format and the day value
																// is valid
			switch (getMonth(date)) { // switch statement used to go through possible month values
			case 1: // case if month is January
			case 3: // case if month is March
			case 5: // case if month is May
			case 7: // case if month is July
			case 8: // case if month is August
			case 10: // case if month is October
			case 12: // case if month is December
				if (getDay(date) <= 31) { // if it is any of these months it should have 31 or less days in the date
					return true; // if so return true
				}
				break; // break out of switch statement if executed
			case 2: // case if the month is February
				if (isLeapYear(getYear(date))) { // check to see if it a leap year
					if (getDay(date) <= 29) { // check to see if the month has 29 or less days in the date
						return true; // if so return true
					}
				} else if (getDay(date) <= 28) { // if it isn't a leap year then it should have 28 or less days in the
													// date
					return true; // if so return true
				}
				break; // break out switch statement if executed
			case 4: // case if the month is April
			case 6: // case if the month is June
			case 9: // case if the month is September
			case 11: // case if the month November
				if (getDay(date) <= 30) { // if it is any of these months then it should have 30 or less days in the date
					return true; // if so return true
				}
				break;
			default: // default case
				return false;
			}
		}
		return false; // return false is the date format isn't valid
	}

	public static int compare(String date1, String date2) { // method used to compare two dates
		if ((isValidDate(date1)) && isValidDate(date2)) { // check to see if both date are in a valid format
			return date1.compareTo(date2); // compare the two dates and return the value
		} else {
			return 0; // if the date format is not valid return 0
		}
	}
}
