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
    private int location;
    private int find;
    private double balance;
    private int loyal;
    private String acctType;
    private Profile profile;
    private Date date;
    private String[] terminalInput;
    public AccountDatabase db = new AccountDatabase();

    /**
     * Main method that runs the entire program.
     */
    public void run() {
        System.out.println("Bank Teller is running.\n");
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            StringTokenizer str = new StringTokenizer(sc.nextLine());
            terminalInput = new String[str.countTokens()];

            try {
                for (int i = 0; i < terminalInput.length; i++) {
                    terminalInput[i] = str.nextToken();
                }

                switch (terminalInput[0]) {
                    case "O":
                        openAccount(terminalInput);
                        break;
                    case "C":
                        closeAccount(terminalInput);
                        break;
                    case "D":
                        depositAccount(terminalInput);
                        break;
                    case "W":
                        withdrawAccount(terminalInput);
                        break;
                    case "P":
                        printAccount();
                        break;
                    case "PT":
                        printByAccountType();
                        break;
                    case "PI":
                        printWithInterestAndFees();
                        break;
                    case "UB":
                        updateAccountBalance(terminalInput);
                        break;
                    case "Q":
                        System.out.println("Bank Teller is terminated.");
                        break;
                    default:
                        System.out.println("Invalid command!");
                }
            } catch (RuntimeException e){ }
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
        openAllAccountsButCheckings(inputArray);
        openCheckings(inputArray);
    }

    /**
     * Helper method for openAccount() that shortens the length of the
     * open method. It deals with Checking accounts.
     */
    private void openCheckings(String[] inputArray) {
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            if(acctType.equals("C")) {
                Checking c = new Checking(profile, balance, false);
                find = db.findDupe(c);
                if(find == 1) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + c.getType() + ") is in the database.");
                } else {
                    db.open(c);
                    System.out.println("Account opened.");
                }

            }
        }
    }

    /**
     * Helper method for openAccount() that shortens the length of the
     * open method. It deals with Money Market, Savings, and College Checkings.
     */
    private void openAllAccountsButCheckings(String[] inputArray) {
        if(inputArray.length == MAX_INPUT_SIZE){// || inputArray.length == MAX_MM_INPUT_SIZE) {
            if(acctType.equals("MM")) {
                if(balance < MIN_BAL) {
                    System.out.println("Minimum of $2500 to open a Money Market account.");
                    return;
                }
                MoneyMarket mm = new MoneyMarket(profile, balance, false, true, 0);
                find = db.findDupe(mm);
                if(find == 1) {
                    System.out.println(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + "same account(" + mm.getType() + ") is in database.");
                } else {
                    db.open(mm);
                    System.out.println("Account opened.");
                }
            } else if(acctType.equals("CC")) {
                int loc = Integer.parseInt(inputArray[6]);
                if(isValidLocation(loc)) {
                    location = loc;
                    CollegeChecking cc = new CollegeChecking(profile, balance, false, location);
                    find = db.findDupe(cc);
                    if (find == 1) {
                        System.out.println(profile.getfName() + " " + profile.getlName()
                                + " " + profile.getDob() + "same account(" + cc.getType() + ") is in database.");
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
                            + " " + profile.getDob() + "same account(" + s.getType() + ") is in database.");
                } else {
                    db.open(s);
                    System.out.println("Account opened");
                }
            }
        }
    }

    /**
     * Closes an account for the user.
     */
    private void closeAccount(String[] inputArray) {
        if(db.isEmpty()) {
            System.out.print("Account database is empty!");
            return;
        }

        Account acct = createAccountForProcessing(inputArray);
        if(db.close(acct)) {
            System.out.println("Account closed.");
        } else {
            System.out.println("Account not found.");
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
        try {
            double amount = Double.parseDouble(inputArray[5]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        if(Double.parseDouble(inputArray[5]) < 1) {
            System.out.println("Deposit - amount cannot be 0 or negative.");
            return;
        }

        Account acct = createAccountForProcessing(inputArray);
        db.deposit(acct);
        System.out.println("Deposit - balance updated.");

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
        try {
            amount = Double.parseDouble(inputArray[5]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        Account acct = createAccountForProcessing(inputArray);
        if(amount > acct.balance) {
            System.out.println("Withdraw - insufficient funds.");
        }
        if(amount < 1) {
            System.out.println("Withdraw - amount cannot be 0 or negative.");
        }

        if(db.withdraw(acct)) {
            System.out.println("Withdrawal - balance updated.");
        } else {
            System.out.println("Non existing account");
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
     * is used to process the account internally.
     * @param inputArray
     * @return Account
     */
    private Account createAccountForProcessing(String[] inputArray) {
        acctType = inputArray[1];
        balance = Double.parseDouble(inputArray[5]);
        date = new Date(inputArray[4]);
        profile = new Profile(inputArray[2], inputArray[3], date);
        Account acct;

        switch(acctType) {
            case "C":
                acct = new Checking(profile, balance, false);
                break;
            case "MM":
                acct = new MoneyMarket(profile, balance, false, true, 0);
                break;
            case "CC":
                int location = Integer.parseInt(inputArray[6]);
                acct = new CollegeChecking(profile, balance, false, location);
                break;
            case "S":
                int loyal = Integer.parseInt(inputArray[6]);
                acct = new Savings(profile, balance, false, isLoyal(loyal));
                break;
            default:
                acct = null;
        }

        return acct;
    }
}





