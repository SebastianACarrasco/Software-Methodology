package src;

/**
 * This is the Account class responsible for all the different types of accounts
 * including regular checking, savings, college checking, and money market.
 * This class has generalized methods for all the different types of accounts
 * and holds the holders profile
 *
 * @author Sebastinan Carrasco, Rachael Chin
 */

public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;
    //int numberOfWithdrawals = 0;


    /**
     *Constructor for the Account class, taking in the holders profile which
     * contains their first name, last name, and dob. This also includes the
     * account balance, and if the account is closed or not. This class carries
     * generalized methods for all the different types of accounts and
     * constructor.
     * @param holder
     * @param balance
     * @param closed
     */
    public Account(Profile holder, double balance, boolean closed) {
        this.holder = holder;
        this.balance = balance;
        this.closed = closed;
    }

    /**
     * Checks if two accounts are equal. This method is generalized for all the
     * different types of accounts under this abstract class.
     * @param obj
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Account) {
            Account other = (Account) obj;
            return this.getType().equals(other.getType())
                    && this.holder.equals(other.holder);
        }
        return false;
    }

    /**
     * Returns all the account information as a string. Generalized for all the
     * different types of accounts.
     * @return account information as a string
     */
    @Override
    public String toString() {
        //acct type:: FN LN DOB :: balance $00.00 :: location
        return getType() + "::" + holder.toString() + ":: $" + balance + "::";
                //+ getLocation();
    }

    /**
     * Withdraws the amount from the account of the holder. Generalized for
     * all the different types of accounts.
     * @param amount to withdraw
     */
    public void withdraw(double amount) {
            this.balance -= amount;
    }

    /**
     * helper method to checks to see if account is closed or not
     * @return true if account is closed false otherwise
     */
    public boolean isClosed() {
        if(this.closed) {
            return true;
        }
        return false;
    }


    /**
     * Deposits the amount holders input. Generalized for
     * all the different types of accounts.
     * @param amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Abstract method that calculates interest for the accounts.
     * @return interest rate for specified account
     */
    public abstract double monthlyInterest(); //return the monthly interest

    /**
     * Abstract method that calculates fee for the accounts.
     * @return fee for specified account
     */
    public abstract double fee(); //return the monthly fee

    /**
     * Abstract method that returns which account type it is.
     * @return account type
     */
    public abstract String getType(); //return the account type (class name)

    //have a get balance getter method gets the amount to withdraw or deposit -> we
    // do this using bank teller so when bank teller bc every account would have a withdraw or deposit and amount value
}



