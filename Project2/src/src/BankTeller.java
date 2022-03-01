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
    private static final int NEWBRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private static final int MIN_BAL_ACCT = 1;
    private static final int MAX_INPUT_SIZE = 7;
    private static final int SUB_MAX_INPUT_SIZE = 6;
    private static final int CLOSE_MAX_INPUT_SIZE = 5;
    private int location;
    private int find;
    private double balance;
    private int loyal;
    private String acctType;
    private Profile profile;
    private String fn;
    private String ln;
    private Date dob;
    private Date date;
    private String[] terminalInput;
    public AccountDatabase db = new AccountDatabase();

    /**
     * Main method that runs the entire program.
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
                if (terminalInput[0].equals("O")) {
                    openAccount();
                } else if (terminalInput[0].equals("C")) {
                    closeAccount(terminalInput);
                } else if (terminalInput[0].equals("D")) {
                    depositAccount(terminalInput);
                } else if (terminalInput[0].equals("W")) {
                    withdrawAccount(terminalInput);
                } else if (terminalInput[0].equals("P")) {
                    printAccount();
                } else if (terminalInput[0].equals("PT")) {
                    printByAccountType();
                } else if (terminalInput[0].equals("PI")) {
                    printWithInterestAndFees();
                } else if (terminalInput[0].equals("UB")) {
                    updateAccountBalance(terminalInput);
                } else if (terminalInput[0].equals("Q")) {
                    System.out.println("Bank Teller is terminated.");
                } else {
                    System.out.println("Invalid command!");
                }
            } catch (RuntimeException e) {
                System.out.println("I hate this");
            }
        }
    }

    private void openAccount() {
        if (terminalInput.length < 6) {
            System.out.println("Missing data for opening an account");
            return;
        }

        if (terminalInput[1].equals("C")) {
            openChecking(terminalInput);
        } else if(terminalInput[1].equals("CC")) {
            openCollegeChecking(terminalInput);
        } else if(terminalInput[1].equals("S")) {
            openSavings(terminalInput);
        } else if(terminalInput[1].equals("MM")) {
            openMoneyMarket(terminalInput);
        }
    }


    /**
     * Validates location input from user.
     * @param location
     * @return true is location is valid, false otherwise.
     */
    private boolean isValidLocation(int location){
        if(location == NEWBRUNSWICK || location == NEWARK || location == CAMDEN){
            return true;
        }
        return false;
    }

    /**
     * Opens an account for the user. This method is separated into its individual
     * accounts to make it easier to read and understand.
     */
    /*
    private void openAccount(String[] inputArray) {
        if(inputArray.length < 6) {
            System.out.println("Missing data for opening an account");
            return;
        }

        acctType = inputArray[1];
        try {
            balance = Double.parseDouble(inputArray[5]);
            date = new Date(inputArray[4]);
            profile = new Profile(inputArray[2], inputArray[3], date);

            if (balance < MIN_BAL_ACCT) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return;
            }
            if (!date.isValid()) {
                System.out.println("Date of birth invalid.");
                return;
            }
        } catch(NumberFormatException e) {
            System.out.println("Not a valid amount");
        }

        //we're doing this below because they have different input sizes
        if(acctType.equals("C")) {
            openCheckings(inputArray);
        } else {
            openAllAccountsButCheckings(inputArray);
        }
    }

     */

    /**
     * Helper method for openAccount(). It deals with Checking accounts.
     */
    private void openChecking(String[] inputArray) {
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            balance = Double.parseDouble(inputArray[5]);
            dob = new Date(inputArray[4]);
            profile = new Profile(inputArray[2], inputArray[3], dob);

            Checking c = new Checking(profile, balance, false);
            find = db.findDupe(c);
            if(find == 1) {
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
     * Helper method for openAccount(). It deals with College Checking accounts.
     */
    private void openCollegeChecking(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE) {
            int loc = Integer.parseInt(inputArray[6]);
            if (isValidLocation(loc)) {
                balance = Double.parseDouble(inputArray[5]);
                dob = new Date(inputArray[4]);
                profile = new Profile(inputArray[2], inputArray[3], dob);
                CollegeChecking cc = new CollegeChecking(profile, balance, false, loc);

                find = db.findDupe(cc);
                if (find == 1) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");
                } else if (!db.open(cc)) {
                    System.out.println("Cannot open CC because user has a C account");

                } else {
                    db.open(cc);
                    System.out.println("Account opened.");
                }
            }
        } else {
            System.out.println("Invalid campus code.");
        }
    }

    /**
     * Helper method for openAccount(). It deals with Savings accounts.
     * @param inputArray
     */
    private void openSavings(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE) {
            //1 = loyal, 0 = not loyal
            loyal = Integer.parseInt(inputArray[6]);
            boolean isLoyal = isLoyal(loyal);
            balance = Double.parseDouble(inputArray[5]);
            dob = new Date(inputArray[4]);
            profile = new Profile(inputArray[2], inputArray[3], dob);

            Savings s = new Savings(profile, balance, false, isLoyal);
            find = db.findDupe(s);
            if (find == 1) {
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
     * Helper method for openAccount(). It deals with Money Market accounts.
     * @param inputArray
     */
    private void openMoneyMarket(String[] inputArray) {
        if (inputArray.length == SUB_MAX_INPUT_SIZE) {
            balance = Double.parseDouble(inputArray[5]);
            dob = new Date(inputArray[4]);
            profile = new Profile(inputArray[2], inputArray[3], dob);

            if (balance < MIN_BAL) {
                System.out.println("Minimum of $2500 to open a Money Market account.");
                return;
            }

            MoneyMarket mm = new MoneyMarket(profile, balance, false, true, 0);
            find = db.findDupe(mm);
            if (find == 1) {
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
     * Helper method for openAccount() that shortens the length of the
     * open method. It deals with Money Market, Savings, and College Checkings.
     */
    /*
    private void openAllAccountsButCheckings(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE){
            if(acctType.equals("MM")) {
                if(balance < MIN_BAL) {
                    System.out.println("Minimum of $2500 to open a Money Market account.");
                    return;
                }
                MoneyMarket mm = new MoneyMarket(profile, balance, false, true, 0);
                find = db.findDupe(mm);
                if(find == 1) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + mm.getType() + ") is in database.");
                } else {
                    db.open(mm);
                    System.out.println("Account opened.");
                }
            } else if(acctType.equals("CC")) {
                int loc = Integer.parseInt(inputArray[6]);
                if(isValidLocation(loc)) {
                    CollegeChecking cc = new CollegeChecking(profile, balance, false, loc);

                    find = db.findDupe(cc);
                    if (find == 1) {
                        System.out.println(profile.getfName() + " " + profile.getlName()
                                + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");
                    } else if(!db.open(cc)) {
                        System.out.println("Cannot open CC because user has a C account");

                    } else {
                        db.open(cc);
                        System.out.println("Account opened.");
                    }
                } else {
                    System.out.println("Invalid campus code.");
                }
            } else if(acctType.equals("S")) {
                //1 = loyal, 0 = not loyal
                loyal = Integer.parseInt(inputArray[6]);
                boolean isLoyal = isLoyal(loyal);
                Savings s = new Savings(profile, balance, false, isLoyal);
                find = db.findDupe(s);
                if(find == 1) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + s.getType() + ") is in database.");
                } else {
                    db.open(s);
                    System.out.println("Account opened");
                }
            }
        } else {
            System.out.println("Invalid command! ");
        }
    }

     */

    /**
     * Closes an account for the user.
     */
    private void closeAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.print("Account database is empty!");
            return;
        }

        if(inputArray.length == CLOSE_MAX_INPUT_SIZE) {
            Account acct = createAccountForClosing(inputArray);
            if (db.close(acct)) {
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
     */
    private void depositAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            try {
                double amount = Double.parseDouble(inputArray[5]);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid amount.");
                return;
            }
            if (Double.parseDouble(inputArray[5]) < 1) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return;
            }

            Account acct = createAccountForDepositingAndWithdrawing(inputArray);
            db.deposit(acct);
            System.out.println("Deposit - balance updated.");
        } else {
            System.out.println("Missing data for depositing money.");
        }

    }

    /**
     * Withdraw money from an account.
     */
    private void withdrawAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        double amount = 0;
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            try {
                amount = Double.parseDouble(inputArray[5]);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid amount.");
                return;
            }
            Account acct = createAccountForDepositingAndWithdrawing(inputArray);

            if (amount < 1) {
                System.out.println("Withdraw - amount cannot be 0 or negative.");
                return;
            } else if (db.withdraw(acct)) {
                if (acct.getType().equals("MM")) {
                    acct.numberOfWithdrawals++;
                }
                System.out.println("Withdrawal - balance updated.");
            } else {
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
        System.out.println("\n*list of accounts in database*");
        db.print();
        System.out.println("*end of list*");
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
        System.out.println("*end of list*");
    }

    /**
     * Prints all accounts in the system with interest and fees.
     */
    private void printWithInterestAndFees() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        System.out.println("\n*list of accounts with fees and monthly interests*");
        db.printFeeAndInterest();
        System.out.println("\n*end of list*");
    }

    /**
     * Updates the balance of an existing account.
     */
    private void updateAccountBalance(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        db.updateAllBalances();
        db.printFeeAndInterest();

    }

    /**
     * Helper method to check if account is loyal or not
     */
    private boolean isLoyal(int loyal) {
        if(loyal == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method that creates an instance of account in terminal and
     * is used for depositing into an account.
     */
    private Account createAccountForDepositingAndWithdrawing(String[] inputArray) {
        acctType = inputArray[1];
        balance = Double.parseDouble(inputArray[5]);
        dob = new Date(inputArray[4]);
        profile = new Profile(inputArray[2], inputArray[3], dob);
        Account acct;

        switch(acctType) {
            case "C":
                acct = new Checking(profile, balance, false);
                break;
            case "MM":
                acct = new MoneyMarket(profile, balance, false, true, 0);
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
     * is used to close an account.
     * @param inputArray
     * @return Account
     */
    private Account createAccountForClosing(String[] inputArray) {
        acctType = inputArray[1];
        balance = Double.parseDouble(inputArray[5]);
        dob = new Date(inputArray[4]);
        profile = new Profile(inputArray[2], inputArray[3], dob);
        Account acct;

        switch(acctType) {
            case "C":
                acct = new Checking(profile, balance, true);
                break;
            case "MM":
                acct = new MoneyMarket(profile, balance, true, false, 0);
                break;
            case "CC":
                acct = new CollegeChecking(profile, balance, true, 0);
                break;
            case "S":
                acct = new Savings(profile, balance, true, false);
                break;
            default:
                acct = null;
        }

        return acct;
    }
}





