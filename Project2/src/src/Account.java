package src;

/**
 * This is the Account class responsible for all the different types of accounts
 * including regular checking, savings, college checking, and money market.
 * This class has generalized methods for all the different types of accounts
 * and holds the holders profile
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;
    public int numberOfWithdrawals = 0;


    /**
     * Constructor for the Account class, taking in the holders profile which
     * contains their first name, last name, and dob. This also includes the
     * account balance, and if the account is closed or not. This class carries
     * generalized methods for all the different types of accounts and
     * constructor.
     * @param holder of type Profile
     * @param balance of type double
     * @param closed of type boolean
     */
    public Account(Profile holder, double balance, boolean closed) {
        this.holder = holder;
        this.balance = balance;
        this.closed = closed;
    }

    /**
     * Checks if two accounts are equal.
     * @param obj
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Account) {
            Account other = (Account) obj;
            if (this.getType().equals(other.getType()) &&
                    this.holder.equals(other.holder)) {
                return true;
            }
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
        if(closed) {
            return getType() + "::" + holder.toString() + ":: balance $" + String.format("%.2f",balance)
                    + "::CLOSED";
        }
        return getType() + "::" + holder.toString() + "::balance $" + String.format("%.2f",balance);
    }

    /**
     * Withdraws the amount from the account of the holder. Generalized for
     * all the different types of accounts.
     * @param amount to withdraw
     */
    public void withdraw(double amount) {
        numberOfWithdrawals++;
        this.balance -= amount;
    }

    /**
     * Deposits the amount holders input. Generalized for
     * all the different types of accounts.
     * @param amount to deposit
     */
    public void deposit(double amount) {
        if(this.closed) { this.closed = false; }
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

}



