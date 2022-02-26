package src;

/**
 * This is the CollegeChecking class responsible for all college checkings accounts.
 * It has the capacity to get the monthly interest, fees, and the type of account
 * @author Sebastian Carrasco, Rachael Chin
 */
public class CollegeChecking extends Checking {
    private double balance;
    private int location;
    private static final double INTEREST = 0.0025/12;
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
    public CollegeChecking (Profile holder, double balance, boolean closed,
                             int location){
        super(holder, balance, closed);
        this.balance = balance;
        this.location = location; //assuming location is correct
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
        return "CollegeCheckings";
    }


    /**
     * Gets the location of the account
     * @return location of the account as a string
     */

    public String getLocation() {
        //to get the location we need to create an instance of that object and then try to get the location it will work
        if (location == NEWBRUNSWICK) {
            return "New Brunswick";
        } else if (location == NEWARK) {
            return "Newark";
        } else {
            return "Camden";
        }

    }

    /**
     * Returns all the account information as a string.
     * @return account information as a string
     */
    @Override
    public String toString() {
        //acct type:: FN LN DOB :: balance $00.00 :: location
        if(this.closed) {
            return getType() + "::" + holder.toString() + "::balance $" + balance
                    + "CLOSED::" + getLocation();
        }
        return getType() + "::" + holder.toString() + "::balance $" + balance + "::"
                + getLocation();
    }

}

