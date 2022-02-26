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
    private String[] inputArray;
    public AccountDatabase db = new AccountDatabase();

    /**
     * Main method that runs the entire program.
     */
    public void run() {
        System.out.println("Bank Teller is running.");
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            StringTokenizer str = new StringTokenizer(sc.nextLine());
            inputArray = new String[str.countTokens()];

            for(int i = 0; i < inputArray.length; i++) {
                inputArray[i] = str.nextToken();
            }

            switch(inputArray[0]) {
                case "O": openAccount();
                    break;
                case "C": closeAccount();
                    break;
                case "D": depositAccount();
                    break;
                case "W": withdrawAccount();
                    break;
                case "P": printAccount();
                    break;
                case "PT": printByAccountType();
                    break;
                case "PI": printWithInterestAndFees();
                    break;
                case "UB": updateAccountBalance();
                case "Q":
                    System.out.println("Bank Teller is terminated.");
                    break;
                default:
                    System.out.println("Invalid command!");
            }
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
     * Opens an account for the user.
     */
    private void openAccount() {
        if(inputArray.length < 6) {
            System.out.println("Missing data for opening account");
            return;
        }

        acctType = inputArray[1];
        balance = Double.parseDouble(inputArray[5]);
        date = new Date(inputArray[4]);
        profile = new Profile(inputArray[2], inputArray[3], date);

        if(balance < MIN_BAL_ACCT) {
            System.out.println("Amount invalid. Initial deposit cannot be less than $1");
            return;
        }
        if(!date.isValid()) {
            System.out.println("Date invalid. Please enter date in the format MM/DD/YYYY");
            return;
        }

        //we're doing this below because they have different input sizes
        openAllAccountsButCheckings();
        openCheckings();

        /*
        //dealing with Savings, College Checkings, and Money Market accounts
        if(inputArray.length == MAX_INPUT_SIZE) {
            if(acctType.equals("MM")) {
                if(balance < MIN_BAL) {
                    System.out.println("Amount invalid. Minimum balance for Money Market account is $2500");
                    return;
                }
                MoneyMarket mm = new MoneyMarket(profile, balance, false, true);
                find = db.findDupe(mm);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(mm);
                }
            } else if(acctType.equals("CC") && isValidLocation(Integer.parseInt(inputArray[6]))) {
                location = Integer.parseInt(inputArray[6]);
                CollegeChecking cc = new CollegeChecking(profile, balance, false, location);
                find = db.findDupe(cc);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(cc);
                }

            } else if(acctType.equals("S")) {
                //1 = loyal, 0 = not loyal
                loyal = Integer.parseInt(inputArray[6]);
                boolean isLoyal = isLoyal(loyal);
                Savings s = new Savings(profile, balance, false, isLoyal);
                find = db.findDupe(s);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(s);
                }
            }
        } else {
            System.out.println("Invalid location.");
            return;
        }


        //rest of accounts which would be checking
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            if(acctType.equals("C")) {
                Checking c = new Checking(profile, balance, false);
                find = db.findDupe(c);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(c);
                }

            }
        }
         */
        System.out.println("Account opened"); //if all cases fail then acct valid
    }

    /**
     * Helper method for openAccount() that shortens the length of the
     * open method. It deals with Checking accounts.
     */
    private void openCheckings() {
        if(inputArray.length == SUB_MAX_INPUT_SIZE) {
            if(acctType.equals("C")) {
                Checking c = new Checking(profile, balance, false);
                find = db.findDupe(c);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(c);
                }

            }
        }
    }

    /**
     * Helper method for openAccount() that shortens the length of the
     * open method. It deals with Money Market, Savings, and College Checkings.
     */
    private void openAllAccountsButCheckings() {
        if(inputArray.length == MAX_INPUT_SIZE) {
            if(acctType.equals("MM")) {
                if(balance < MIN_BAL) {
                    System.out.println("Amount invalid. Minimum balance for Money Market account is $2500");
                    return;
                }
                MoneyMarket mm = new MoneyMarket(profile, balance, false, true);
                find = db.findDupe(mm);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(mm);
                }
            } else if(acctType.equals("CC") && isValidLocation(Integer.parseInt(inputArray[6]))) {
                location = Integer.parseInt(inputArray[6]);
                CollegeChecking cc = new CollegeChecking(profile, balance, false, location);
                find = db.findDupe(cc);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(cc);
                }

            } else if(acctType.equals("S")) {
                //1 = loyal, 0 = not loyal
                loyal = Integer.parseInt(inputArray[6]);
                boolean isLoyal = isLoyal(loyal);
                Savings s = new Savings(profile, balance, false, isLoyal);
                find = db.findDupe(s);
                if(find == 1) {
                    System.out.println("Account of same type already exists.");
                } else {
                    db.open(s);
                }
            }
        } else {
            System.out.println("Invalid location.");
            return;
        }
    }

    /**
     * Closes an account for the user.
     */
    private void closeAccount(Account account) {
        if(db.isEmpty()) {
            System.out.print("Account database is empty.");
            return;
        }

        db.close(account);
    }

    /**
     * Deposit money into an account.
     */
    private void depositAccount(Account account) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        db.deposit(account);
    }

    /**
     * Withdraw money from an account.
     */
    private void withdrawAccount(Account account) {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

        db.withdraw(account);
    }

    /**
     * Print all accounts in the system.
     */
    private void printAccount() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }

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

        db.printFeeAndInterest();
    }

    /**
     * Updates the balance of an existing account.
     */
    private void updateAccountBalance() {
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }


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


}




