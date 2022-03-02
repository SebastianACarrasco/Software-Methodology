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
     * Helper method to check if database is empty. It will be empty if the
     * first index is null because the array is initialized to size 4.
     * @return true if database is empty, false if not
     */
    public boolean isEmpty() {
        if (this.accounts[0] == null) {
            return true;
        }
        return false;
    }

    /**
     * Finds an account in the database
     * @param account
     * @return index of account in the array if found, -1 if not found
     */
    private int find(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if (this.accounts[i] != null && this.accounts[i].equals(account)) {
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
     * This method updates all balances by calling in the fees and interest rates again
     */
    public void updateAllBalances() {
        for(int i = 0; i < this.numAcct; i++) {
            if(this.accounts[i] != null) {
                if(this.accounts[i].balance > this.accounts[i].fee() + this.accounts[i].monthlyInterest()) {
                    this.accounts[i].balance = this.accounts[i].balance -
                            this.accounts[i].fee() + this.accounts[i].monthlyInterest();
                }
            }
        }
    }

    /**
     * Opens a new account in the database with the given account type if
     * it is not already in the database
     * @param account
     * @return true if account is successfully added, false if not
     */
    public boolean open(Account account) {
        int index = this.find(account);
        boolean isCC = account.getType().equals("CollegeCheckings");

        if(isCC && index == NOT_FOUND) {
            for(int i = 0; i < this.numAcct; i++) {
                if(this.accounts[i] != null && this.accounts[i].holder.equals(account.holder)
                        && this.accounts[i].getType().equals("Checking")) {
                    return false;
                }
            }
        }
        if (index == NOT_FOUND) {
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
     * Helper method used to see if there is an account in the database of the
     * same type already in existence because find method is private
     * @return 1 if there is an account of the same type, 0 if not
     */
    public int findDupe(Account account) {
        int index = this.find(account);
        if(index != NOT_FOUND && this.accounts[index].equals(account)) {
            return 1;
        }
        return 0;
    }

    /**
     * helper method to checks to see if account is closed or not
     * @return true if account is closed false otherwise
     */
    public boolean isClosed(Account account) {
        if(account.closed) {
            return true;
        }
        return false;
    }

    /**
     * Helper method that sets loyal to false when closing an account
     * @param account
     */
    public Account setLoyalFalse(Account account) {
        int index = this.find(account);
        if (this.accounts[index].getType().equals("Money Market Savings")){
            MoneyMarket mm = (MoneyMarket) account;
            if(isClosed(mm)) {
                mm.setLoyal(false);
                return mm;
            }
        } else if (this.accounts[index].getType().equals("Savings")) {
            Savings s = (Savings) account;
            if(isClosed(s)) {
                s.setLoyal(false);
                return s;
            }
        }

        return account;
    }

    /**
     * Closes an account in the database
     * @param account
     * @return true if account is successfully closed, false if not
     */
    public boolean close(Account account) {
        int index = this.find(account);
        if (index != NOT_FOUND) {
            this.accounts[index].balance = 0;

            if(this.accounts[index].getType().equals("Money Market Savings")) {
                accounts[index].numberOfWithdrawals = 0;
            }

            if(this.accounts[index].getType().equals("Savings")
                    || this.accounts[index].getType().equals("Money Market Savings")) {
                //need to set isLoyal to false after closing
                accounts[index] = setLoyalFalse(accounts[index]);
            }

            this.accounts[index].closed = true;
            return true;
        }

        return false; // account not found
    }

    /**
     * Deposits money into an account of any type
     * @param account
     */
    public void deposit(Account account) {
        int index = this.find(account);

        //checks if account is already in database
        if(this.accounts[index].equals(account) && this.accounts[index].closed) {
            this.accounts[index].closed = false;
            this.accounts[index].deposit(account.balance);
        }
        if (index != NOT_FOUND) {
            accounts[index].deposit(account.balance);
        }
    }

    /**
     * Withdraws money from an account of any type
     * @param account
     * @return true if money was successfully withdrawn, false if not
     */
    public boolean withdraw(Account account) {
        int index = this.find(account);
        if (index != NOT_FOUND && account.balance > 0
                && (accounts[index].balance - account.balance) >= 0) {
            accounts[index].withdraw(account.balance);
            return true;
        }
        return false;
    }

    /**
     * Prints all the accounts in the database
     */
    public void print() {
        for (int i = 0; i < this.numAcct; i++) {
            if(this.accounts[i] != null) {
                System.out.println(this.accounts[i].toString());
            }
        }
    }

    /**
     * Prints all the accounts in the database by order of account type.
     */
    public void printByAccountType() {
        for (int j = 0; j < numAcct; j++) {
            for (int i = j + 1; i < numAcct; i++) {
                if (this.accounts[i] != null
                        && accounts[i].getType().compareTo(accounts[j].getType()) < 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[i];
                    accounts[i] = temp;
                }
            }
        }
        print();
    }

    /**
     * Prints the fee and interest of all the accounts in the database
     */
    public void printFeeAndInterest() {
        for (int i = 0; i < this.numAcct; i++) {
            if(this.accounts[i] != null) {
                System.out.println(this.accounts[i].toString()
                  + " fee $" + String.format("%.2f",this.accounts[i].fee())
                  + " :: monthly interest $"
                  + String.format("%.2f",this.accounts[i].monthlyInterest()));
            }
        }
    }
}




