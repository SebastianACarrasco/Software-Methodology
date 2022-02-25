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
    private int location;
    private double balance;
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
        if(db.isEmpty()) {
            System.out.println("Account database is empty.");
            return;
        }
        if(inputArray.length != 6 || inputArray.length != 7) {
            System.out.println("Missing data for opening account");
            return;
        }

        acctType = inputArray[1];
        balance = Double.parseDouble(inputArray[5]);
        date = new Date(inputArray[4]);
        profile = new Profile(inputArray[2], inputArray[3], date);

        if(balance < 1) {
            System.out.println("Amount invalid. Initial deposit cannot be less than $1");
            return;
        }
        if(!date.isValid()) {
            System.out.println("Date invalid. Please enter date in the format MM/DD/YYYY");
            return;
        }

        //dealing with Savings and Money Market accounts
        if(inputArray.length == 7 && isValidLocation(Integer.parseInt(inputArray[6]))) {
            location = Integer.parseInt(inputArray[6]);
            if(acctType.equals("MM")) {
                if(balance < MIN_BAL) {
                    System.out.println("Amount invalid. Minimum balance for Money Market account is $2500");
                    return;
                }
            }
        } else {
            System.out.println("Invalid location.");
            return;
        }

        //rest of accounts
        if(inputArray.length == 6) {

        }


        System.out.println("Account opened"); //if all cases fail then acct valid
    }

    /**
     * Closes an account for the user.
     */
    private void closeAccount() {

    }

    /**
     * Deposit money into an account.
     */
    private void depositAccount() {

    }

    /**
     * Withdraw money from an account.
     */
    private void withdrawAccount() {

    }

    /**
     * Print all accounts in the system.
     */
    private void printAccount() {
        db.print();
    }

    /**
     * Prints all accounts in the system ordered by account type.
     */
    private void printByAccountType() {
        db.printByAccountType();
    }

    /**
     * Prints all accounts in the system with interest and fees.
     */
    private void printWithInterestAndFees() {
        db.printFeeAndInterest();
    }

    /**
     * Updates the balance of an existing account.
     */
    private void updateAccountBalance() {

    }


}




