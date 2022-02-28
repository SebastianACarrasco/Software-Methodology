package src;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyMarketTest {

    @Test
    public void monthlyInterest() {
        AccountDatabase accountDatabase = new AccountDatabase();

        //monthly interst for moneymarket account that is loyal
        Date d3 = new Date("10/10/2001");
        Profile p3 = new Profile("John", "Doe", d3);
        Account moneymarket = new MoneyMarket(p3, 100, false, true, 0);
        assertEquals(moneymarket.balance*0.0095/12, moneymarket.monthlyInterest(), 0.001);


        //monthly interst for moneymarket account that is not loyal
        Date d2 = new Date("10/10/2001");
        Profile p2 = new Profile("John", "Doe", d2);
        Account moneymarket2 = new MoneyMarket(p3, 100, false, false, 0);
        assertEquals(moneymarket2.balance*0.008/12, moneymarket2.monthlyInterest(), 0.001);
    }
}


