package src;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the open and close methods in the Account Database class.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class AccountDatabaseTest {

    @Test
    public void open() {
        AccountDatabase accountDatabase = new AccountDatabase();

        //open a checkings account
        Date d1 = new Date("11/10/2001");
        Profile p1 = new Profile("Sebastian", "Carrasco", d1);
        Account checkings = new Checking(p1, 100, false);
        assertTrue(accountDatabase.open(checkings));

        //open a duplicated checkings account
        Date d7 = new Date("11/10/2001");
        Profile p7 = new Profile("Sebastian", "Carrasco", d7);
        Account checkings7 = new Checking(p7, 100, false);
        assertFalse(accountDatabase.open(checkings7));

        //open college checkings account
        Date d2 = new Date("12/15/2000");
        Profile p2 = new Profile("Rachael", "Chin", d2);
        Account collegecheckings = new CollegeChecking(p2, 10000, false, 0);
        assertTrue(accountDatabase.open(collegecheckings));

        //open a money market account
        Date d3 = new Date("10/10/2000");
        Profile p3 = new Profile("John", "Doe", d3);
        Account moneymarket = new MoneyMarket(p3, 10000, false, true, 0);
        assertTrue(accountDatabase.open(moneymarket));

        //open a savings account
        Date d4 = new Date("11/10/2001");
        Profile p4 = new Profile("Sebastian", "Carrasco", d4);
        Account savings = new Savings(p4, 100, false, true);
        assertTrue(accountDatabase.open(savings));

        accountDatabase.print();


    }

    @Test
    public void close() {
        AccountDatabase accountDatabase = new AccountDatabase();

        //close checkings
        Date d1 = new Date("11/10/2001");
        Profile p1 = new Profile("Sebastian", "Carrasco", d1);
        Account checkings = new Checking(p1, 100, false);
        accountDatabase.open(checkings);
        assertTrue(accountDatabase.close(checkings));

        //close savings
        Date d2 = new Date("11/10/2001");
        Profile p2 = new Profile("Sebastian", "Carrasco", d1);
        Account savings = new Savings(p1, 100, false, true);
        accountDatabase.open(savings);
        assertTrue(accountDatabase.close(savings));

        //close college checkings
        Date d3 = new Date("11/10/2001");
        Profile p3 = new Profile("Sebastian", "Carrasco", d1);
        Account collegecheckings = new CollegeChecking(p1, 100, false, 1);
        accountDatabase.open(collegecheckings);
        assertTrue(accountDatabase.close(collegecheckings));

        //close money market
        Date d4 = new Date("11/10/2001");
        Profile p4 = new Profile("Sebastian", "Carrasco", d1);
        Account moneymarket = new MoneyMarket(p1, 100, false, true, 0);
        accountDatabase.open(moneymarket);
        assertTrue(accountDatabase.close(moneymarket));
    }

}