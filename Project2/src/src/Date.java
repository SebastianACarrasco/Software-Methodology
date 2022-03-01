package src;
import java.util.Calendar;
import java.util.StringTokenizer;
/**
 * The Date class takes in a String in the format "mm/dd/yyyy" and creates a Date
 *  * object with attributes month, day, and year, or can do so by default is we are
 *  * using the current calendar date. It also can compare two Date objects
 *  * to one another to check for equality, return the Date in a toString format,
 *  * and check if the Date is a valid given date while accounting for leap year variability.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class Date {

    private int year;
    private int month;
    private int day;
    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    public static final int INV = -1;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int DAYS31 = 31;
    public static final int DAYS30 = 30;
    public static final int FEBDAYS = 28;
    public static final int LEAPFEB = 29;
    public static final int MAXYEAR = 2022;

    /**
     * take “mm/dd/yyyy” and create a Date object
     * @param date the String representation of the date
     */
    public Date(String date) {
        StringTokenizer dd = new StringTokenizer( date, "/");
        month = Integer.parseInt(dd.nextToken());
        day = Integer.parseInt(dd.nextToken());
        year = Integer.parseInt(dd.nextToken());
    }

    /**
     * create an object with today’s date (see Calendar class)
     */
    public Date() {
        String today = Calendar.getInstance().getTime().toString();
        String[] dateArr = today.split(" ");
        month = getMonth(dateArr[1]);
        day = Integer.parseInt(dateArr[2]);
        year = Integer.parseInt(dateArr[5]);
    }

    /**
     * Get the month as an integer using a String
     * @param mm where it is string representation of the month
     * @return an integer that will get the integer representation of the month
     */
    private int getMonth(String mm){
        switch (mm) {
            case "Jan":
                return JAN;
            case "Feb":
                return FEB;
            case "Mar":
                return MAR;
            case "Apr":
                return APR;
            case "May":
                return MAY;
            case "Jun":
                return JUN;
            case "Jul":
                return JUL;
            case "Aug":
                return AUG;
            case "Sep":
                return SEP;
            case "Oct":
                return OCT;
            case "Nov":
                return NOV;
            case "Dec":
                return DEC;
            default:
                return INV; //rturn -1 or invalid if the month is not in the range of real months
        }
    }

    /**
     * Checks to see if a date is valid
     * @return a true value/boolean if the date is valid, false if it is not
     */
    public boolean isValid() {
        if (this.month < JAN || this.month > DEC) {
            return false;
        } else if (this.year > MAXYEAR) {
            return false;
        }
        else {
            if (this.day <= 0) {
                return false;
            } else if (this.month == JAN || this.month == MAR || this.month == MAY || this.month == JUL
                    || this.month == AUG || this.month == OCT || this.month == DEC) {
                if (day > DAYS31) {
                    return false;
                }
            } else if (this.month == APR || this.month == JUN || this.month == SEP || this.month == NOV) {
                if (day > DAYS30) {
                    return false;
                }
            } else {
                if (isLeapYear(year)) {
                    if (day > LEAPFEB) {
                        return false;
                    }
                } else {
                    if (day > FEBDAYS) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * isLeapYear will check to see if a year is a leap year
     * @param year is the integer representation of the year
     * @return a boolean or true value if it is a leap year, and false if it is not
     */
    private boolean isLeapYear(int year){
        if(year % QUADRENNIAL == 0) {
            if(year % CENTENNIAL == 0) {
                if(year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * The compareTo method will compare the current date with a given date to see if it's a
     * previous date, future date, or the same date
     * @param date the object representation of the date
     * @return an integer to see if the given year, month or date is less than,
     * greater than, or equal to current year, month, or date and will return either -1, 1, or
     * 0 accordingly
     */
    public int compareTo(Date date) {
        if ( this.year > date.year ) {
            return 1;
        } else if ( this. year < date.year ) {
            return -1;
        } else {
            //same year
            if ( this.month > date.month ) {
                return 1;
            } else if ( this.month < date.month ) {
                return -1;
            } else {
                //same month
                if ( this.day > date.day){
                    return -1;
                } else if ( this.day < date.day) {
                    return 1;
                } else {
                    return 0; // the same
                }
            }
        }
    }

    /**
     * The toString method will return a string representation of the object
     * @return a String literal for the month, day, and
     * year in the formal of mm/dd/yyyy
     */
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static void main(String[] args) {
        // this test case is to test if the current date 2/8/22 is a valid date
        Date date1 = new Date();
        boolean expectedValue = true;
        boolean currentValue = date1.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        /*this test case is to test if the date 2/29/2020 is a valid leap year,
        which it is and should thus return false
         */
        Date date2 = new Date("2/29/2020");
        expectedValue = true;
        currentValue = date2.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        // this test case is to test that 2/29/2021 is NOT a valid date
        Date date3 = new Date("2/29/2021");
        expectedValue = false;
        currentValue = date3.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 13/1/2022 is invalid
        Date date4 = new Date("13/1/2022");
        expectedValue = false;
        currentValue = date4.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 1/31/2022 is invalid
        Date date5 = new Date("1/31/2022");
        expectedValue = false;
        currentValue = date5.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 1/32/2021 is invalid
        Date date6 = new Date("1/32/2021");
        expectedValue = false;
        currentValue = date6.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 4/31/2021 is invalid
        Date date7 = new Date("4/31/2021");
        expectedValue = false;
        currentValue = date7.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date -4/2/2021 is invalid
        Date date8 = new Date("-4/2/2021");
        expectedValue = false;
        currentValue = date8.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 2/-1/2021 is invalid
        Date date9 = new Date("2/-1/2021");
        expectedValue = false;
        currentValue = date9.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 0/1/2021 is invalid
        Date date10 = new Date("0/1/2021");
        expectedValue = false;
        currentValue = date10.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        //this test case is to test that an invalid date 2/0/2021 is invalid
        Date date11 = new Date("2/0/2021");
        expectedValue = false;
        currentValue = date11.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        // this test case is to test that 2/28/2021 is a valid date
        Date date12 = new Date("2/28/2021");
        expectedValue = true;
        currentValue = date12.isValid();
        if (expectedValue == currentValue) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
}



