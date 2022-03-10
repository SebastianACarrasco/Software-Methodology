package com.example.project3;

/**
 * Savings class extends Account class and creates a savings account
 * with the given interest rate and fees. Using Account abstract class,
 * it can get account type, fee, and interest rate.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class Savings extends Account {
    protected boolean isLoyal;
    private static final double MIN_BALANCE = 300;
    private static final double LOYAL_INTEREST = 0.0045/12;
    private static final double NONLOYAL_INTEREST = 0.003/12;
    private static final double FEE = 6.0;

    /**
     * Constructor for Savings class that takes information from account class
     * @param holder of type Profile
     * @param balance of type double
     * @param closed of type boolean
     * @param isLoyal of type boolean
     */
    public Savings(Profile holder, double balance, boolean closed, boolean isLoyal) {
        super(holder, balance, closed);
        this.isLoyal = isLoyal;
    }

    /**
     * Setter method for loyalty
     * @param isLoyal
     */
    public void setLoyal(boolean isLoyal){
        this.isLoyal = isLoyal;
    }

    /**
     * Getter method for loyalty
     * @return loyalty as a string which called in toString()
     */
    public String getIsLoyal(){
        if(this.isLoyal){
            return "::Loyal";
        }
        return "";
    }

    /**
     * Returns all the account information as a string.
     * @return account information as a string
     */
    @Override
    public String toString() {
        if(this.closed) {
            return getType() + "::" + holder.toString() + "::balance $" + String.format("%.2f",balance)
                    + getIsLoyal() + ":: CLOSED";
        }
        return getType() + "::" + holder.toString() + "::balance $" + String.format("%.2f",balance)
                + getIsLoyal();
    }

    /**
     * Helps with finding interest rate depending on account information and
     * data. Used from Account abstract class.
     * @return monthy interest rate as double
     */
    @Override
    public double monthlyInterest(){
        if(isLoyal) {
            return this.balance * LOYAL_INTEREST;//loyal customers get .15% more interest
        } else {
            return this.balance * NONLOYAL_INTEREST;
        }
    }

    /**
     * Helps with finding monthly fee depending on account information and data.
     * used from Account abstract class.
     * @return monthly fee as double
     */
    @Override
    public double fee() {
        if(balance >= MIN_BALANCE) {
            return 0; //no fee because balance is >= 300
        } else {
            return FEE;
        }
    }

    /**
     * Gets the account type, used from Account abstract class.
     * @return account type as String
     */
    @Override
    public String getType(){
        return "Savings";
    }
}




