package src;

import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void isValid() {
        //checks for current date
        Date date1 = new Date();
        assertTrue(date1.isValid());

        //checks for leap year
        Date date2 = new Date("2/29/2020");
        assertTrue(date2.isValid());

        //checks future date
        Date date3 = new Date("3/20/2021");
        assertTrue(date3.isValid());

        //invalid month date
        Date date4 = new Date("13/1/2022");
        assertFalse(date4.isValid());

        //invalid day date
        Date date6 = new Date("1/32/2021");
        assertFalse(date6.isValid());

        //wrong days in month
        Date date7 = new Date("4/31/2021");
        assertFalse(date7.isValid());

        //checks for negatives
        Date date9 = new Date("2/-1/2021");
        assertFalse(date9.isValid());

        //checks for date beyond 2022
        Date date10 = new Date("1/1/2023");
        assertFalse(date10.isValid());
    }
}



