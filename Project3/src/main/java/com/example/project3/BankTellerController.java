package com.example.project3;
/**
 * This is the controller class for the BankTeller. Here we deal with program
 * handling and user input from GUI.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.StringTokenizer;

public class BankTellerController {
    private static final int NEW_BRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int Camden = 2;
    private static final int NO_DUPE = 0;
    private static final int MIN_BALANCE = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private static final int FOUND_DUPE = 1;
    private int find;
    private String firstName;
    private String lastName;
    private Profile profile;
    private double totalAmount;
    private Date date;
    public AccountDatabase db = new AccountDatabase();
    @FXML
    private RadioButton loyal, nb, camden, newark;
    @FXML
    private RadioButton moneymarket, savings, collegechecking, checkings;
    @FXML
    private RadioButton savingstab2, mmTab2, ccTab2, checkingTab2;
    @FXML
    private TextField fn, fnTab2, ln, lnTab2, amount, amountTab2;
    @FXML
    private TextArea information, printInfo, transactions;
    @FXML
    private DatePicker dob, dobTab2;


    /**
     * Helper method to setup a temp account which is used to deposit and withdraw.
     */
    private Account setupAccount() {
        Account tmp = null;
        if (savings.isSelected() && !isInputNull()) {
            tmp = new Savings(profile, totalAmount, false, false);
        } else if (moneymarket.isSelected() && !isInputNull()) {
            tmp = new MoneyMarket(profile, totalAmount, false, false);
        } else if (collegechecking.isSelected() && !isInputNull()) {
            tmp = new CollegeChecking(profile, totalAmount, false, 0);
        } else if(checkings.isSelected() && !isInputNull()) {
            tmp = new Checking(profile, totalAmount, false);
        }
        return tmp;
    }

    /**
     * Disables radio buttons that are originally disabled but were enabled
     * with user interaction.
     */
    private void disableRadioButtons() {
        nb.setDisable(true);
        camden.setDisable(true);
        newark.setDisable(true);
        loyal.setDisable(true);
    }

    /**
     * Resets all fields to default states.
     */
    private void resetForm() {
        fn.clear();
        ln.clear();
        amount.clear();
        moneymarket.setSelected(false);
        savings.setSelected(false);
        collegechecking.setSelected(false);
        loyal.setSelected(false);
        nb.setSelected(false);
        camden.setSelected(false);
        newark.setSelected(false);
        nb.setDisable(true);
        camden.setDisable(true);
        newark.setDisable(true);
        loyal.setDisable(true);
    }

    /**
     * Checks for user input and if anything is null.
     * @return true if any inputs are null and false if they are not
     */
    private boolean isInputNull() {
        if(fn.getText().isEmpty() || ln.getText().isEmpty() || dob.getValue() == null
                || amount.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for first name in text field.
     * @param event
     */
    @FXML
    public void getFirstName(ActionEvent event) {
        String tmp = fn.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.firstName = st.nextToken();
    }

    /**
     * Getter for first name in text field for deposit/withdraw.
     * @param event
     */
    @FXML
    public void getFirstNameTab2(ActionEvent event) {
        String tmp = fnTab2.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.firstName = st.nextToken();

    }

    /**
     * Getter for last name in text field.
     * @param event
     */
    @FXML
    public void getLastName(ActionEvent event) {
        String tmp = ln.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.lastName = st.nextToken();
    }

    /**
     * Getter for last name in text field for deposit/withdraw.
     * @param event
     */
    @FXML
    public void getLastNameTab2(ActionEvent event) {
        String tmp = lnTab2.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.lastName = st.nextToken();
    }

    /**
     * Getter for dob in text field for deposit/withdraw.
     * @param event
     */
    @FXML
    public void getDOBTab2(ActionEvent event) {
        //yyyy-mm-dd to mm/dd/yyyy
        StringTokenizer st = new StringTokenizer(dobTab2.getValue().toString(), "/");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        this.date = new Date(month+"/"+day+"/"+year);
    }

    /**
     * Getter for date of birth in date picker. Tokenizes the date for input
     * into database and check for validation.
     * @param event
     */
    @FXML
    public void getDOB(ActionEvent event) {
        //yyyy-mm-dd to mm/dd/yyyy
        StringTokenizer st = new StringTokenizer(dob.getValue().toString(), "/");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        this.date = new Date(month+"/"+day+"/"+year);
    }

    /**
     * Getter for amount in text field.
     * @param event
     */
    @FXML
    public void getAmountTab2(ActionEvent event) {
        this.totalAmount = Double.parseDouble(amountTab2.getText());
    }

    /**
     * Getter for amount in text field.
     * @param event
     */
    @FXML
    public void getAmount(ActionEvent event) {
        this.totalAmount = Double.parseDouble(amount.getText());
    }

    /**
     * Checks if the CC radio button is not selected to trigger other radio
     * buttons that work with CC.
     */
    @FXML
    public void ccNotSelected() {
        if(!collegechecking.isSelected()) {
            disableRadioButtons();
        }
    }

    /**
     * Checks if the Savings radio button is not selected to trigger other radio
     * buttons that work with Savings.
     */
    @FXML
    public void savingsNotSelected() {
        if(!savings.isSelected()) {
            disableRadioButtons();
        }
    }

    /**
     * Checks if any other radio button but Savings or CC are selected
     * because Savings and CC have their own method as they have other
     * dependencies with other radio buttons. Disables if not Savings or CC.
     */
    @FXML
    public void notSavingsOrCC() {
        if(moneymarket.isSelected()) {
            disableRadioButtons();
        } else if(checkings.isSelected()) {
            disableRadioButtons();
        }
    }

    /**
     * Checks if CC radio button is selected to trigger other radio buttons that
     * are dependent on CC.
     */
    @FXML
    public void isCollegeChecking() {
        if(collegechecking.isSelected()) {
            nb.setDisable(false);
            camden.setDisable(false);
            newark.setDisable(false);
            loyal.setDisable(true);
        }
    }

    /**
     * Checks if Savings radio button is selected to trigger other radio buttons that
     * are dependent on Savings.
     */
    @FXML
    public void isSavings() {
        if(savings.isSelected()) {
            loyal.setDisable(false);
            nb.setDisable(true);
            camden.setDisable(true);
            newark.setDisable(true);
        }
    }


    /**
     * Opens new account into the database. This method takes care of date and
     * initial deposit edge cases.
     * @param event
     */
    @FXML
    public void openAccount(ActionEvent event) {
        if(isInputNull()) {
            information.appendText("Please fill out all fields\n");
            return;
        }
        getFirstName(event);
        getLastName(event);
        getDOB(event);
        getAmount(event);
        if(!date.isValid()) {
            information.appendText("Date of birth Invalid\n");
            return;
        }

        try {
            if (this.totalAmount < MIN_BALANCE) {
                information.appendText("Initial deposit cannot be 0 or negative \n");
                return;
            }
        } catch (NumberFormatException e) {
            information.appendText("Not a valid amount \n");
            return;
        }

        this.profile = new Profile(firstName, lastName, date);
        if(savings.isSelected()) {
            openSavingsAccount();
        } else if(collegechecking.isSelected()) {
            openCollegeCheckingAccount();
        } else if(moneymarket.isSelected()) {
            openMoneyMarketAccount();
        } else if(checkings.isSelected()) {
            openCheckingAccount();
        }
        resetForm();
    }

    /**
     * Opens a new savings account into the database.
     */
    @FXML
    public void openSavingsAccount() {
        boolean isLoyal = false;
        if (loyal.isSelected()) {
            isLoyal = true;
        }

        Savings s = new Savings(profile, totalAmount, false, isLoyal);
        find = db.findDupe(s);
        if (db.findClosedProfile(s)) {
            db.open(s);
            information.appendText("Account reopened \n");
        } else if (find == FOUND_DUPE) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + s.getType() + ") is in the database.\n");
        } else {
            db.open(s);
            information.appendText("Account opened \n");
        }
    }

    /**
     * Opens a new money market account into the database.
     */
    @FXML
    public void openMoneyMarketAccount() {
        if (totalAmount < MIN_BAL) {
            information.appendText("Minimum of $2500 to open a Money Market account.\n");
            return;
        }
        MoneyMarket mm = new MoneyMarket(profile, totalAmount, false, true);
        find = db.findDupe(mm);
        if (db.findClosedProfile(mm)) {
            db.open(mm);
            information.appendText("Account reopened \n");
        } else if (find == FOUND_DUPE) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + mm.getType() + ") is in the database.\n");
        } else {
            db.open(mm);
            information.appendText("Account opened \n");
        }
    }

    /**
     * Opens a new college checking account into the database.
     */
    @FXML
    public void openCollegeCheckingAccount() {
        int loc = 0;
        if (nb.isSelected()) {
            loc = NEW_BRUNSWICK;
        } else if (camden.isSelected()) {
            loc = CAMDEN;
        } else if (newark.isSelected()) {
            loc = NEWARK;
        }

        CollegeChecking cc = new CollegeChecking(profile, totalAmount, false, loc);

        find = db.findDupe(cc);
        if (db.findClosedProfile(cc)) {
            db.open(cc);
            information.appendText("Account reopened.\n");
        } else if (find == FOUND_DUPE) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.\n");
        } else if (!db.open(cc)) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.\n");
        } else {
            db.open(cc);
            information.appendText("Account opened.\n");
        }
    }

    /**
     * Opens a new checking account into the database.
     */
    @FXML
    public void openCheckingAccount() {
        Checking c = new Checking(profile, totalAmount, false);
        find = db.findDupe(c);
        if (db.findClosedProfile(c)) {
            db.open(c);
            information.appendText("Account reopened \n");
        } else if (find == FOUND_DUPE) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + c.getType() + ") is in the database.\n");
            return;
        } else {
            db.open(c);
            information.appendText("Account opened \n");
        }
    }

    /**
     * Closes an account in the database. Takes care of edge cases that involve
     * locating existing and nonexisting accounts as well as misinputs.
     */
    @FXML
    public void closeAccount() {
        if (db.isEmpty()) {
            information.appendText("Account database is empty!");
            return;
        }

        Account tmp = setupAccount();
        if(tmp == null) {
            information.appendText("Missing information for closing.\n");
        }

        if (!db.close(tmp)) {
            information.appendText("Account is closed already.\n");
        } else if (db.close(tmp)) {
            information.appendText("Account closed.\n");
        } else {
            information.appendText("Account not found.\n");
        }
        this.amount.setText("");
    }

    /**
     * Prints account information to the text area.
     */
    @FXML
    public void printAccount() {
        if(db.isEmpty()) {
            printInfo.appendText("Account database is empty.\n");
            return;
        }
        printInfo.appendText("\n*list of accounts in the database*\n");
        String[] output = db.print();
        for (String s : output) {
            if(s != null) {
                printInfo.appendText(s + "\n");
            }
        }
        printInfo.appendText("*end of list*\n");
    }

    /**
     * Prints account information to the text area ordered by account type.
     */
    @FXML
    public void printByAccountType() {
        if (db.isEmpty()) {
            printInfo.appendText("Account database is empty.\n");
            return;
        }
        printInfo.appendText("\n*list of accounts by account type*\n");
        String[] output = db.printByAccountType();
        for (String s : output) {
            if(s != null) {
                printInfo.appendText(s + "\n");
            }
        }
        printInfo.appendText("*end of list*\n");
    }

    /**
     * Prints account information to the text area with interest and fees.
     */
    @FXML
    public void printWithInterestAndFees() {
        if (db.isEmpty()) {
            printInfo.appendText("Account database is empty.\n");
            return;
        }

        printInfo.appendText("\n*list of accounts with fees and monthly interests*\n");
        String[] output = db.printFeeAndInterest();
        for (String s : output) {
            if(s != null) {
                printInfo.appendText(s + "\n");
            }
        }

        printInfo.appendText("*end of list*\n");
    }

    /**
     * Prints account information to the text area with updated account balances.
     */
    @FXML
    public void updateAccountBalance() {
        if(db.isEmpty()) {
            printInfo.appendText("Account database is empty.\n");
            return;
        }
        db.updateAllBalances();
        printInfo.appendText("\n*list of accounts with updated balance\n");
        printAccount();
    }

    /**
     * Deposits money into an account and takes care of edge cases such as
     * not a valid amount or reopening existing accounts.
     * @param event
     */
    @FXML
    public void depositFromAccount(ActionEvent event) {
        getFirstNameTab2(event);
        getLastNameTab2(event);
        getDOBTab2(event);
        getAmountTab2(event);
        if(!date.isValid()) {
            transactions.appendText("Date of birth Invalid\n");
            return;
        }
        if (db.isEmpty()) {
            transactions.appendText("Account database is empty.\n");
            return;
        }
        try {
            totalAmount = Double.parseDouble(amountTab2.getText());
        } catch (NumberFormatException e) {
            transactions.appendText("Not a valid amount.\n");
            return;
        }
        if (Double.parseDouble(amountTab2.getText()) < MIN_BALANCE) {
            transactions.appendText("Deposit - amount cannot be 0 or negative.\n");
            return;
        }

        Account tmp = setupAccount();
        if(tmp == null) {
            information.appendText("Missing information for depositing.\n");
        }

        if(db.findDupe(tmp) == NO_DUPE) {
            transactions.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " " + tmp.getType()
                    + " is not in database.\n");
            return;
        }
        if(tmp.closed) {
            db.deposit(tmp);
            System.out.println("Account reopened.\n");
            return;
        }
        db.deposit(tmp);
        System.out.println("Deposit - balance updated.\n");
    }

    /**
     * Withdraws money from an account and takes care of edge cases such as
     * not a valid amount or withdrawing from nonexisting acount.
     */
    @FXML
    public void withdrawFromAccount() {
        if(db.isEmpty()) {
            transactions.appendText("Account database is empty.\n");
            return;
        }
        try {
            totalAmount = Double.parseDouble(amountTab2.getText());
        } catch (NumberFormatException e) {
            transactions.appendText("Not a valid amount.\n");
            return;
        }

        Account tmp = setupAccount();
        if(tmp == null) {
            information.appendText("Missing information for withdrawing.\n");
        }

        if(db.findDupe(tmp) == NO_DUPE) {
            transactions.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " " + tmp.getType()
                    + "is not in database.\n");
            return;
        }

        if (totalAmount < MIN_BALANCE) {
            transactions.appendText("Withdraw - amount cannot be 0 or negative.\n");
            return;
        }
        if (db.withdraw(tmp)) {
            if (tmp.getType().equals("MM")) {
                tmp.numberOfWithdrawals++;
            }
            transactions.appendText("Withdrawal - balance updated.\n");
        } else if (!db.withdraw(tmp)){
            System.out.println("Withdrawal - insufficient funds.\n");
        }
    }

}



