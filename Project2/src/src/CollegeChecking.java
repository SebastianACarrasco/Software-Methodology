package src;

/**
 * This is the CollegeChecking class responsible for all college checkings accounts.
 * It has the capacity to get the monthly interest, fees, and the type of account
 * @author Rachael Chin
 */
public class CollegeChecking extends Checking{


    private double balance;
    private static final double INTEREST = 100.25/12;
    private static final double COLLEGEFEE = 0.0;
    private static final int NEWBRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int CAMDEN = 2;

    /**
     * Constructor for CollegeChecking class that takes information from account class
     * @param holder for the profile holder
     * @param balance for how much balance remains in the account
     * @param closed whether or not the account is closed
     */
    public CollegeChecking (Profile holder, double balance, boolean closed){
        super(holder, balance, closed);
        this.balance = balance;
    }

    /**
     * Helps with finding interest rate depending on account information and
     * data. Used from Account abstract class.
     * @return monthly interest rate as a double
     */
    @Override
    public double monthlyInterest() {
        double interest = this.balance * INTEREST;
        return interest;
    }

    /**
     * Helps with finding monthly fee depending on account information and data.
     * used from Account abstract class.
     * @return monthly fee as a double
     */
    @Override
    public double fee() {
        return COLLEGEFEE;
    }

    /**
     * Gets the account type, used from Account abstract class.
     * @return a String of the type of account
     */
    @Override
    public String getType() {
        return "College Checkings";
    }

}
