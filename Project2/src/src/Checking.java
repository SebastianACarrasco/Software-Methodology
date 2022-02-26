package src;

/**
 * This is the Checking class responsible for all regular checkings accounts.
 * It has the capacity to get the monthly interest, fees, and the type of account
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class Checking extends Account{

    private double balance;
    double interest;
    private static final double FEE = 25.0;
    private static final double MIN_FEE = 0.0;
    private static final double MIN_BALANCE = 1000.0;
    private static final double INTEREST = 0.001/12;

    /**
     * Constructor for Checking class that takes information from account class
     * @param holder for the profile holder
     * @param balance for how much balance remains in the account
     * @param closed whether or not the account is closed
     */
    public Checking (Profile holder, double balance, boolean closed) {
        super(holder, balance, closed);
        //this.balance = balance;
    }

    /**
     * Helps with finding interest rate depending on account information and
     * data. Used from Account abstract class.
     * @return monthly interest rate as a double
     */
    @Override
    public double monthlyInterest() {
        double interests = this.balance * INTEREST;
        interest = interests;
        return interests;
    }

    /**
     * Helps with finding monthly fee depending on account information and data.
     * used from Account abstract class.
     * @return monthly fee as a double
     */
    @Override
    public double fee() {
        if (this.balance >= MIN_BALANCE) {
            return MIN_FEE;
        } else {
            return FEE;
        }
    }

    /**
     * Gets the account type, used from Account abstract class.
     * @return a String of the type of account
     */
    @Override
    public String getType() {
        return "Checking";
    }

}
