package src;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyMarketTest {

    @Test
    public void monthlyInterest() {
        AccountDatabase accountDatabase = new AccountDatabase();

        Date d3 = new Date("10/10/2001");
        Profile p3 = new Profile("John", "Doe", d3);
        Account moneymarket = new MoneyMarket(p3, 100, false, true);
        moneymarket.monthlyInterest();
        //assertEquals(100, moneymarket.getBalance(), 0.01);
    }
}