package src;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is the main interface in which the user can input their account
 * information for any processing including openings and closing an account,
 * depositing and withdrawing money, and different ways of printing the
 * information. All private methods take care of errors inputted by user.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

public class BankTeller {
    private static final int INPUT_COMMAND = 0;
    private static final int INPUT_ACCT_TYPE = 1;
    private static final int INPUT_FN = 2;
    private static final int INPUT_LN = 3;
    private static final int INPUT_DOB = 4;
    private static final int INPUT_BALANCE = 5;
    private static final int INPUT_LOCATION = 6;
    private static final int INPUT_LOYAL = 6;
    private static final int FOUND_DUPE = 1;
    private static final int NO_DUPE = 0;
    private static final int NEW_BRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int IS_LOYAL = 1;
    private static final int MIN_BALANCE = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private static final int MAX_INPUT_SIZE = 7;
    private static final int SUB_MAX_INPUT_SIZE = 6;
    private static final int CLOSE_MAX_INPUT_SIZE = 5;
    private int find;
    private double balance;
    private int loyal;
    private String acctType;
    private Profile profile;
    private Date dob;
    private String[] terminalInput;
    public AccountDatabase db = new AccountDatabase();

    /**
     * Main method that runs the entire user interface.
     */
    public void run() {
        System.out.println("Bank Teller is running.\n");
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            StringTokenizer str = new StringTokenizer(sc.nextLine());
            terminalInput = new String[str.countTokens()];
            int count = 0;

            while (str.hasMoreTokens()) {
                terminalInput[count] = str.nextToken();
                count++;
            }
            if (count == 0) continue;

            try {
                if (terminalInput[INPUT_COMMAND].equals("O")) {
                    openAccount();
                } else if (terminalInput[INPUT_COMMAND].equals("C")) {
                    closeAccount(terminalInput);
                } else if (terminalInput[INPUT_COMMAND].equals("D")) {
                    depositAccount(terminalInput);
                } else if (terminalInput[INPUT_COMMAND].equals("W")) {
                    withdrawAccount(terminalInput);
                } else if (terminalInput[INPUT_COMMAND].equals("P")) {
                    printAccount();
                } else if (terminalInput[INPUT_COMMAND].equals("PT")) {
                    printByAccountType();
                } else if (terminalInput[INPUT_COMMAND].equals("PI")) {
                    printWithInterestAndFees();
                } else if (terminalInput[INPUT_COMMAND].equals("UB")) {
                    updateAccountBalance(terminalInput);
                } else if (terminalInput[INPUT_COMMAND].equals("Q")) {
                    System.out.println("Bank Teller is terminated.");
                    System.exit(0);
                } else {
                    System.out.println("Invalid command!");
                }
            } catch (RuntimeException e) {
                System.out.println("Runtime exception: " + e.getMessage());
           }
        }
    }

    /**
     * Private method that will open an account, or will display error messages for
     * missing data and invalid input.
     */
    private void openAccount() {
        if (terminalInput.length < SUB_MAX_INPUT_SIZE) {
            System.out.println("Missing data for opening an account");
            return;
        }

        try {
            double amount = Double.parseDouble(terminalInput[INPUT_BALANCE]);
            if (amount < MIN_BALANCE) {
                System.out.println("Initial deposit cannot be 0 or negative");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return;
        }

        if (terminalInput[INPUT_ACCT_TYPE].equals("C")) {
            openChecking(terminalInput);
        } else if(terminalInput[INPUT_ACCT_TYPE].equals("CC")) {
            openCollegeChecking(terminalInput);
        } else if(terminalInput[INPUT_ACCT_TYPE].equals("S")) {
            openSavings(terminalInput);
        } else if(terminalInput[INPUT_ACCT_TYPE].equals("MM")) {
            openMoneyMarket(terminalInput);
        }
    }

    /**
     * Validates location input from user.
     * @param location
     * @return true is location is valid, false otherwise.
     */
    private boolean isValidLocation(int location){
        if(location == NEW_BRUNSWICK || location == NEWARK || location == CAMDEN){
            return true;
        }
        return false;
    }

    /**
     * Helper method for openAccount(). It deals with opening Checking accounts.
     * @param inputArray
     */
    private void openChecking(String[] inputArray) {
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            balance = Double.parseDouble(inputArray[INPUT_BALANCE]);
            dob = new Date(inputArray[INPUT_DOB]);
            if(!dob.isValid()) {System.out.println("Date of Birth invalid"); return;}

            profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);

            Checking c = new Checking(profile, balance, false);
            find = db.findDupe(c);

            if(db.findClosedProfile(c)) {
                db.open(c);
                System.out.println("Account reopened.");
            } else if(find == FOUND_DUPE) {
                System.out.println(profile.getfName() + " " + profile.getlName()
                        + " " + profile.getDob() + " same account(" + c.getType() + ") is in the database.");
            } else {
                db.open(c);
                System.out.println("Account opened.");
            }
        } else {
            System.out.println("Missing data for opening an account");
        }
    }

    /**
     * Helper method for openAccount(). It deals with opening
     * College Checking accounts.
     * @param inputArray
     */
    private void openCollegeChecking(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE) {
            int loc = Integer.parseInt(inputArray[INPUT_LOCATION]);
            if (isValidLocation(loc)) {
                balance = Double.parseDouble(inputArray[INPUT_BALANCE]);
                dob = new Date(inputArray[INPUT_DOB]);
                if(!dob.isValid()) {System.out.println("Date of Birth invalid"); return;}
                profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);
                CollegeChecking cc = new CollegeChecking(profile, balance, false, loc);

                find = db.findDupe(cc);

                if(db.findClosedProfile(cc)) {
                    db.open(cc);
                    System.out.println("Account reopened.");
                } else
                    if (find == FOUND_DUPE) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");
                } else if (!db.open(cc)) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");

                } else {
                    db.open(cc);
                    System.out.println("Account opened.");
                }
            } else {
                System.out.println("Invalid campus code.");
            }
        }
    }

    /**
     * Helper method for openAccount(). It deals with opening Savings accounts.
     * @param inputArray
     */
    private void openSavings(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE) {
            //1 = loyal, 0 = not loyal
            loyal = Integer.parseInt(inputArray[INPUT_LOYAL]);
            boolean isLoyal = isLoyal(loyal);
            balance = Double.parseDouble(inputArray[INPUT_BALANCE]);
            dob = new Date(inputArray[INPUT_DOB]);
            if(!dob.isValid()) {System.out.println("Date of Birth invalid"); return;}
            profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);

            Savings s = new Savings(profile, balance, false, isLoyal);
            find = db.findDupe(s);
            if(db.findClosedProfile(s)) {
                db.open(s);
                System.out.println("Account reopened.");
            } else
                if (find == FOUND_DUPE) {
                System.out.println(profile.getfName() + " " + profile.getlName()
                        + " " + profile.getDob() + " same account(" + s.getType() + ") is in database.");
            } else {
                db.open(s);
                System.out.println("Account opened");
            }
        } else {
            System.out.println("Missing data for opening a Savings account");
        }
    }

    /**
     * Helper method for openAccount(). It deals with opening Money Market accounts.
     * @param inputArray
     */
    private void openMoneyMarket(String[] inputArray) {
        if (inputArray.length == SUB_MAX_INPUT_SIZE) {
            balance = Double.parseDouble(inputArray[INPUT_BALANCE]);
            dob = new Date(inputArray[INPUT_DOB]);
            if(!dob.isValid()) {System.out.println("Date of Birth invalid"); return;}
            profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);

            if (balance < MIN_BAL) {
                System.out.println("Minimum of $2500 to open a Money Market account.");
                return;
            }

            MoneyMarket mm = new MoneyMarket(profile, balance, false, true, 0);

            find = db.findDupe(mm);

            if(db.findClosedProfile(mm)) {
                db.open(mm);
                System.out.println("Account reopened.");
            } else
                if (find == FOUND_DUPE) {
                System.out.println(profile.getfName() + " " + profile.getlName()
                        + " " + profile.getDob() + " same account(" + mm.getType() + ") is in database.");
            } else {
                db.open(mm);
                System.out.println("Account opened.");
            }
        } else {
            System.out.println("Missing data for opening a Money Market account");
        }
    }

    /**
     * Closes an account.
     * @param inputArray
     */
    private void closeAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.print("Account database is empty!");
            return;
        }

        if(inputArray.length == CLOSE_MAX_INPUT_SIZE) {
            Account acct = createAccountForClosing(inputArray);
            if (!db.close(acct)) {
                System.out.println("Account is closed already.");
            } else if (db.close(acct)) {
                System.out.println("Account closed.");
            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Missing data for closing an account.");
        }
    }


    /**
     * Deposit money into an account.
     * @param inputArray
     */
    private void depositAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            try {
                double amount = Double.parseDouble(inputArray[INPUT_BALANCE]);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid amount.");
                return;
            }
            if (Double.parseDouble(inputArray[INPUT_BALANCE]) < MIN_BALANCE) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return;
            }

            Account acct = createAccountForDepositingAndWithdrawing(inputArray);

            if(db.findDupe(acct) == NO_DUPE) {
                System.out.println(profile.getfName() + " " + profile.getlName()
                        + " " + profile.getDob() + " " + acct.getType()
                        + " is not in database.");
                return;
            }

            if(acct.closed) {
                db.deposit(acct);
                System.out.println("Account reopened.");
                return;
            }
            db.deposit(acct);
            System.out.println("Deposit - balance updated.");
        } else {
            System.out.println("Missing data for depositing money.");
        }

    }

    /**
     * Withdraw money from an account.
     * @param inputArray
     */
    private void withdrawAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        double amount = 0;
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            try {
                amount = Double.parseDouble(inputArray[INPUT_BALANCE]);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid amount.");
                return;
            }
            Account acct = createAccountForDepositingAndWithdrawing(inputArray);

            if(db.findDupe(acct) == NO_DUPE) {
                System.out.println(profile.getfName() + " " + profile.getlName()
                        + " " + profile.getDob() + " " + acct.getType()
                        + "is not in database.");
                return;
            }

            if (amount < MIN_BALANCE) {
                System.out.println("Withdraw - amount cannot be 0 or negative.");
                return;
            }
            if (db.withdraw(acct)) {
                if (acct.getType().equals("MM")) {
                    acct.numberOfWithdrawals++;
                }
                System.out.println("Withdrawal - balance updated.");
            } else if (!db.withdraw(acct)){
                System.out.println("Withdrawal - insufficient funds.");
            }
        } else {
            System.out.println("Missing data for withdrawing money.");
        }
    }

    /**
     * Print all accounts in the system.
     */
    private void printAccount() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        System.out.println("\n*list of accounts in the database*");
        db.print();
        System.out.println("*end of list*\n");
    }

    /**
     * Prints all accounts in the system ordered by account type.
     */
    private void printByAccountType() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        System.out.println("\n*list of accounts by account type*");
        db.printByAccountType();
        System.out.println("*end of list*\n");
    }

    /**
     * Prints all accounts in the system with interest and fees.
     */
    private void printWithInterestAndFees() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        System.out.println("*list of accounts with fees and monthly interests*");
        db.printFeeAndInterest();
        System.out.println("*end of list*\n");
    }

    /**
     * Updates the balance of an existing account and prints the database.
     * @param inputArray
     */
    private void updateAccountBalance(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        db.updateAllBalances();
        System.out.println("*list of accounts with updated balance");
        db.print();
        System.out.println("*end of list\n");

    }

    /**
     * Helper method to check if account is loyal or not.
     * @param loyal
     */
    private boolean isLoyal(int loyal) {
        if(loyal == IS_LOYAL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method that creates an instance of the account in terminal and
     * is used for depositing and withdrawing into an account.
     * @param inputArray
     * @return Account
     */
    private Account createAccountForDepositingAndWithdrawing(String[] inputArray) {
        acctType = inputArray[INPUT_ACCT_TYPE];
        balance = Double.parseDouble(inputArray[INPUT_BALANCE]);
        dob = new Date(inputArray[INPUT_DOB]);
        profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);
        Account acct;

        switch(acctType) {
            case "C":
                acct = new Checking(profile, balance, false);
                break;
            case "MM":
                acct = new MoneyMarket(profile, balance, false, false);
                break;
            case "CC":
                acct = new CollegeChecking(profile, balance, false, 0);
                break;
            case "S":
                acct = new Savings(profile, balance, false, false);
                break;
            default:
                acct = null;
        }

        return acct;
    }

    /**
     * Helper method that creates an instance of account in terminal and
     * is used to close an account. This method sets the account to closed and
     * balance 0.
     * @param inputArray
     * @return Account
     */
    private Account createAccountForClosing(String[] inputArray) {
        acctType = inputArray[INPUT_ACCT_TYPE];
        dob = new Date(inputArray[INPUT_DOB]);
        profile = new Profile(inputArray[INPUT_FN], inputArray[INPUT_LN], dob);
        Account acct;

        switch(acctType) {
            case "C":
                acct = new Checking(profile, 0, true);
                break;
            case "MM":
                acct = new MoneyMarket(profile, 0, true, false);
                break;
            case "CC":
                acct = new CollegeChecking(profile, 0, true, 0);
                break;
            case "S":
                acct = new Savings(profile, 0, true, false);
                break;
            default:
                acct = null;
        }
        return acct;
    }
}





