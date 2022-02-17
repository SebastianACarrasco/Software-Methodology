package src;

/**
 * Savings class extends Account class and creates a savings account
 * with the given interest rate and fees. Using Account abstract class,
 * it can get account type, fee, and interest rate.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class Savings extends Account {
    private double balance;
    private boolean isLoyal;
    private static final double MIN_BALANCE = 300;
    private static final double LOYAL_INTEREST = 0.0045;
    private static final double NONLOYAL_INTEREST = 0.003;
    private static final double FEE = 6.0;

    /**
     * Constructor for Savings class that takes information from account class
     * @param holder
     * @param balance
     * @param closed
     * @param isLoyal
     */
    public Savings(Profile holder, double balance, boolean closed, boolean isLoyal) {
        super(holder, balance, closed);
        this.balance = balance;
        this.isLoyal = isLoyal;
    }

    /**
     * Helps with finding interest rate depending on account information and
     * data. Used from Account abstract class.
     * @return monthy interest rate as double
     * TODO: check if this is correct because she gives us annual interest rate
     */
    public double monthlyInterest(){
        if(isLoyal) {
            return LOYAL_INTEREST; //loyal customers get .15% more interest
        } else {
            return NONLOYAL_INTEREST;
        }
    }

    /**
     * Helps with finding monthly fee depending on account information and data.
     * used from Account abstract class.
     * @return monthly fee as double
     */
    public double fee() {
        if(balance >= MIN_BALANCE) {
            return 0.0;
        } else {
            return FEE;
        }
    }

    /**
     * Gets the account type, used from Account abstract class.
     * @return account type as String
     */
    public String getType(){
        return "Savings";
    }
}




