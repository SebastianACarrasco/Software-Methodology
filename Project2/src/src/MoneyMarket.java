package src;

/**
 * This is the MoneyMarket class responsible for all MoneyMarket accounts.
 * It has the capacity to get the monthly interest, fees, and the type of account
 * @author Seabstian Carrasco, Rachael Chin
 */
public class MoneyMarket extends Savings{

    private double balance; // redundant only in account
    private boolean isLoyal; // redundant only in savings
    //private int numberOfWithdrawals = 0;
    private static final double FEE = 10.0;
    private static final double MIN_FEE = 0.0;
    private static final double MIN_BALANCE = 2500.0;
    private static final double REGULARINTEREST = 0.008/12;
    private static final double LOYALINTEREST = 0.0095/12;
    private static final int MAXWITHDRAWALS = 3;

    /**
     * Constructor for CollegeChecking class that takes information from account class
     * @param holder for the profile holder
     * @param balance for how much balance remains in the account
     * @param closed whether or not the account is closed
     */
    public MoneyMarket(Profile holder, double balance, boolean closed, boolean isLoyal) {
        super(holder, balance, closed, isLoyal);
        this.balance = balance;
        this.isLoyal = true; //loyal customer by default
    }


    /**
     * Helps with finding interest rate depending on account information and
     * data. Used from Account abstract class.
     * @return monthly interest rate as a double
     */
    @Override
    public double monthlyInterest() {
        double interest = 0;
        if (this.isLoyal) {
            interest = this.balance * REGULARINTEREST;
        } else {
            interest = this.balance * LOYALINTEREST;
        }
        return interest;
    }

    /**
     * Helps with finding monthly fee depending on account information and data.
     * used from Account abstract class.
     * @return monthly fee as a double
     */
    @Override
    public double fee() {
        //if (this.balance >= MIN_BALANCE && numberOfWithdrawals > MAXWITHDRAWALS) {
        if (this.balance >= MIN_BALANCE) {
            return MIN_FEE;
        } else {
            //case if the balance <2500 or if the number of withdrawals > 3
            this.isLoyal = false; //balance is < 2500 so they are no longer a loyal customer
            return FEE;
        }
    }

    /**
     * Gets the account type, used from Account abstract class.
     * @return a String of the type of account
     */
    @Override
    public String getType() {
        return "Money Market";
    }
}



