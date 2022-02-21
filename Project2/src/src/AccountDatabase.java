package src;
/**
 * This is an array based container database for accounts with all the different
 * types of accounts. This class also prints all the accounts depending on the
 * order inputted by the user. The array does not shrink but increases in size
 * by 4 when it is full.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;
    private static final int NOT_FOUND = -1;


    /**
     * Constructor for the AccountDatabase class.
     * Setting the account array to a size of size 4.
     */
    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = this.accounts.length;
    }

    /**
     * Finds an account in the database
     * @param account
     * @return index of account in the array if found, -1 if not found
     */
    private int find(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i].equals(account)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Grows account array database by 4
     */
    private void grow() {
        Account[] temp = new Account[this.numAcct + 4];
        for (int i = 0; i < this.numAcct; i++) {
            temp[i] = this.accounts[i];
        }
        this.accounts = temp;
        this.numAcct = this.accounts.length;
    }

    /**
     * Opens a new account in the database with the given account type if
     * it is not already in the database
     * @param account
     * @return true if account is successfully added, false if not
     */
    public boolean open(Account account) {
        if (this.find(account) == NOT_FOUND) {
            for(int i = 0; i < this.numAcct; i++) {
                if(this.accounts[i] == null) {
                    this.accounts[i] = account;
                    grow();
                    return true;
                }
            }
        }
        return false; //account already exists
    }

    /**
     * Closes an account in the database
     * @param account
     * @return true if account is successfully closed, false if not
     */
    public boolean close(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if(this.accounts[i].equals(account)) {
                this.accounts[i].balance = 0;

                if(this.accounts[i].getType().equals("Savings")
                || this.accounts[i].getType().equals("MoneyMarket")) {
                    //need to set isLoyal to false after closing
                    //this.accounts[i].setLoyal(false);
                }
                this.accounts[i].closed = true;
                return true;
            }
        }
        return false; //account not found
    }

    /**
     * Deposits money into an account of any type
     * @param account
     */
    public void deposit(Account account) {
        account.deposit(65);
        //just call the method and update it but again idk how to get the amount
    }

    /**
     * Withdraws money from an account of any type
     * @param account
     * @return true if money was successfully withdrawn, false if not
     */
    public boolean withdraw(Account account) {
        if (account.balance > 0){
            //confused because how do we get the amount to withdraw
            double balanceBeforeWithdrawal = account.balance;
            account.withdraw(65);
            if (balanceBeforeWithdrawal   != account.balance) {
                return true;
            }
        }
        return false;

    }

    /**
     * Prints all the accounts in the database
     */
    public void print() {
        for (int i = 0; i < this.numAcct; i++) {
            System.out.println(this.accounts[i].toString());
        }
    }

    /**
     * Prints all the accounts in the database by order of Account Type.
     */
    public void printByAccountType() {
        boolean isSwapped = false;

        do {
            isSwapped = false;
            for (int i = 0; i < numAcct; i++) {
                if(accounts[i].getType().compareTo(accounts[i+1].getType()) > 0) {
                    Account temp = accounts[i+1];
                    accounts[i+1] = accounts[i];
                    accounts[i] = temp;
                    isSwapped = true;
                }
            }
        } while((isSwapped));
    }

    /**
     * Prints the fee and interest of all the accounts in the database
     */
    public void printFeeAndInterest() {
        for (int i = 0; i < this.numAcct; i++) {
            System.out.println(this.accounts[i].toString()
                    + " fee $ " + this.accounts[i].fee()
                    + " :: monthly interest $" + this.accounts[i].monthlyInterest());
        }
    }

}





