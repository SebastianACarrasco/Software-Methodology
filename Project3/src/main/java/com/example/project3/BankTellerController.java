package com.example.project3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.StringTokenizer;

/**
 * This is the controller class for the BankTeller. Here we deal with program
 * handling and user input from GUI.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class BankTellerController {
    private static final int NEW_BRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int NO_DUPE = 0;
    private static final int MIN_BALANCE = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private static final int FOUND_DUPE = 1;
    private int find;
    private boolean closing;
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
    private RadioButton savingsFromWithdrawDeposit;
    @FXML
    private RadioButton mmFromWithdrawDeposit;
    @FXML
    private RadioButton ccFromWithdrawDeposit;
    @FXML
    private RadioButton checkingFromWithdrawDeposit;
    @FXML
    private TextField fn, ln, amount;
    @FXML
    private TextField fnFromWithdrawDeposit, lnFromWithdrawDeposit;
    @FXML
    private TextField amountWithdrawDeposit;
    @FXML
    private TextArea information, printInfo, transactions;
    @FXML
    private DatePicker dob, dobFromWithdrawDeposit;

    /**
     * Helper method to set up a temp account which is used for open/close.
     */
    private Account setupAccount() {
        Account setup = null;
        if (savings.isSelected() && !isInputNull()) {
            setup = new Savings(profile, totalAmount, false, false);
        } else if (moneymarket.isSelected() && !isInputNull()) {
            setup = new MoneyMarket(profile, totalAmount, false, false);
        } else if (collegechecking.isSelected() && !isInputNull()) {
            setup = new CollegeChecking(profile, totalAmount, false, 0);
        } else if(checkings.isSelected() && !isInputNull()) {
            setup = new Checking(profile, totalAmount, false);
        }
        return setup;
    }


    /**
     * Helper method to set up a temp account which is used for deposit/withdraw
     * because both tabs have different ID's.
     */
    private Account setupAccountFromWithdrawDeposit() {
        Account setup = null;
        if (savingsFromWithdrawDeposit.isSelected() && !isInputNullFromWithdrawDeposit()) {
            setup = new Savings(profile, totalAmount, false, false);
        } else if (mmFromWithdrawDeposit.isSelected() && !isInputNullFromWithdrawDeposit()) {
            setup = new MoneyMarket(profile, totalAmount, false, false);
        } else if (ccFromWithdrawDeposit.isSelected() && !isInputNullFromWithdrawDeposit()) {
            setup = new CollegeChecking(profile, totalAmount, false, 0);
        } else if(checkingFromWithdrawDeposit.isSelected() && !isInputNullFromWithdrawDeposit()) {
            setup = new Checking(profile, totalAmount, false);
        }
        return setup;
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
     * Resets all fields to default states for the deposit/withdraw tab.
     */
    private void resetFormFromWithdrawDeposit() {
        fnFromWithdrawDeposit.clear();
        lnFromWithdrawDeposit.clear();
        amountWithdrawDeposit.clear();
        mmFromWithdrawDeposit.setSelected(false);
        savingsFromWithdrawDeposit.setSelected(false);
        ccFromWithdrawDeposit.setSelected(false);
        checkingFromWithdrawDeposit.setSelected(false);
    }


    /**
     * Resets all fields to default states for the open/close tab.
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
        if(!closing) {
            if (fn.getText().isEmpty() || ln.getText().isEmpty() || dob.getValue() == null
                    || amount.getText().isEmpty()) {
                return true;
            }
        } else {
            if (fn.getText().isEmpty() || ln.getText().isEmpty() || dob.getValue() == null)
                return true;
        }
        return false;
    }

    /**
     * Checks for user input and if anything is null for the deposit/withdraw tab
     * because it has different ID's from open/close tab.
     * @return true if any inputs are null and false if they are not
     */
    private boolean isInputNullFromWithdrawDeposit() {
        if(fnFromWithdrawDeposit.getText().isEmpty() || lnFromWithdrawDeposit.getText().isEmpty() || dobFromWithdrawDeposit.getValue() == null
                || amountWithdrawDeposit.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for first name in text field.
     */
    @FXML
    public void getFirstName() {
        String tmp = fn.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.firstName = st.nextToken();
    }

    /**
     * Getter for first name in text field for deposit/withdraw.
     */
    @FXML
    public void getFirstNameFromWithdrawDeposit() {
        String tmp = fnFromWithdrawDeposit.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.firstName = st.nextToken();

    }

    /**
     * Getter for last name in text field.
     */
    @FXML
    public void getLastName() {
        String tmp = ln.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.lastName = st.nextToken();
    }

    /**
     * Getter for last name in text field for deposit/withdraw.
     */
    @FXML
    public void getLastNameFromWithdrawDeposit() {
        String tmp = lnFromWithdrawDeposit.getText();
        StringTokenizer st = new StringTokenizer(tmp);
        this.lastName = st.nextToken();
    }

    /**
     * Getter for dob in text field for deposit/withdraw. Tokenizes the date for input
     * into database and check for validation.
     */
    @FXML
    public void getDOBFromWithdrawDeposit() {
        //yyyy-mm-dd to mm/dd/yyyy
        StringTokenizer st = new StringTokenizer(dobFromWithdrawDeposit.getValue().toString(), "-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        this.date = new Date(month+"/"+day+"/"+year);
    }

    /**
     * Getter for date of birth in date picker. Tokenizes the date for input
     * into database and check for validation.
     */
    @FXML
    public void getDOB() {
        //yyyy-mm-dd to mm/dd/yyyy
        StringTokenizer st = new StringTokenizer(dob.getValue().toString(), "-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        this.date = new Date(month+"/"+day+"/"+year);
    }

    /**
     * Getter for amount in text field from Withdraw/Deposit tab.
     */
    @FXML
    public void getAmountFromWithdrawDeposit() {
        try {
            this.totalAmount = Double.parseDouble(amountWithdrawDeposit.getText());
        } catch (NumberFormatException e) {
            information.appendText("Please enter a valid amount.\n");
        }
    }

    /**
     * Getter for amount in text field in open/close tab.
     */
    @FXML
    public void getAmount() {
        try {
            this.totalAmount = Double.parseDouble(amount.getText());
        } catch (NumberFormatException e) {
            information.appendText("Please enter a valid amount.\n");
            this.totalAmount = 0;
        }
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
     * @param event of type ActionEvent
     */
    @FXML
    public void notSavingsOrCC(ActionEvent  event) {
        if (moneymarket.isSelected()) {
            disableRadioButtons();
        } else if (checkings.isSelected()) {
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
     */
    @FXML
    public void openAccount() {
        if(isInputNull()) {
            information.appendText("Please fill out all fields\n");
            resetForm();
            return;
        }
        getFirstName();
        getLastName();
        getDOB();
        getAmount();
        if(!date.isValid()) {
            information.appendText("Date of birth Invalid\n");
            return;
        }

        if (this.totalAmount < MIN_BALANCE) {
            information.appendText("Initial deposit cannot be 0 or negative \n");
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
        } else {
            information.appendText("Please select a location.\n");
            return;
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
            information.appendText("Account database is empty!\n");
            return;
        }
        if(isInputNull()) {
            information.appendText("Please fill out all fields\n");
            resetForm();
            return;
        }
        this.closing = true;
        getFirstName();
        getLastName();
        getDOB();
        this.profile = new Profile(this.firstName,this.lastName, this.date);
        Account tmp = setupAccount();

        if(tmp == null) {
            information.appendText("Missing information for closing.\n");
            return;
        }
        if (!db.close(tmp)) {
            information.appendText("Account is closed already.\n");
        } else if (db.close(tmp)) {
            information.appendText("Account closed.\n");
            resetForm();
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
     */
    @FXML
    public void depositFromAccount() {
        if(db.isEmpty()) {
            transactions.appendText("Account database is empty.\n");
            return;
        }
        if(isInputNullFromWithdrawDeposit()) {
            transactions.appendText("Please fill out all fields\n");
            return;
        }
        getFirstNameFromWithdrawDeposit();
        getLastNameFromWithdrawDeposit();
        getDOBFromWithdrawDeposit();
        getAmountFromWithdrawDeposit();
        if(!date.isValid()) {
            transactions.appendText("Date of birth Invalid\n");
            return;
        }
        this.profile = new Profile(this.firstName,this.lastName, this.date);
        if (db.isEmpty()) {
            transactions.appendText("Account database is empty.\n");
            return;
        }

        try {
            if(totalAmount >= MIN_BALANCE)
                totalAmount = Double.parseDouble(amountWithdrawDeposit.getText());
            else {
                transactions.appendText("Deposit - amount cannot be 0 or negative.\n");
                return;
            }
        } catch (NumberFormatException e) {
            transactions.appendText("Not a valid amount.\n");
            return;
        }

        Account tmp = setupAccountFromWithdrawDeposit();
        if(tmp == null) {
            transactions.appendText("Missing information for depositing.\n");
            return;
        }
        if(db.findDupe(tmp) == NO_DUPE) {
            transactions.appendText(profile.getfName() + " " + profile.getlName()
                    + " (" + profile.getDob() + ") " + tmp.getType()
                    + " is not in database.\n");
            return;
        }
        if(tmp.closed) {
            db.deposit(tmp);
            transactions.appendText("Account reopened.\n");
            return;
        }
        db.deposit(tmp);
        transactions.appendText("Deposit - balance updated.\n");
        resetFormFromWithdrawDeposit();
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
        if(isInputNullFromWithdrawDeposit()) {
            transactions.appendText("Please fill out all fields\n");
            return;
        }
        getFirstNameFromWithdrawDeposit();
        getLastNameFromWithdrawDeposit();
        getDOBFromWithdrawDeposit();
        getAmountFromWithdrawDeposit();
        if(!date.isValid()) {
            transactions.appendText("Date of birth Invalid\n");
        }
        try {
            totalAmount = Double.parseDouble(amountWithdrawDeposit.getText());
        } catch (NumberFormatException e) {
            transactions.appendText("Not a valid amount.\n");
            return;
        }

        Account tmp = setupAccountFromWithdrawDeposit();
        if(tmp == null) {
            transactions.appendText("Missing information for withdrawing.\n");
            return;
        }

        if (totalAmount < MIN_BALANCE) {
            transactions.appendText("Withdraw - amount cannot be 0 or negative.\n");
            return;
        }

        if(db.findDupe(tmp) == NO_DUPE) {
            transactions.appendText(profile.getfName() + " " + profile.getlName()
                    + " (" + profile.getDob() + ") " + tmp.getType()
                    + "is not in database.\n");
            return;
        }

        if (db.withdraw(tmp)) {
            if (tmp.getType().equals("MM")) {
                tmp.numberOfWithdrawals++;
            }
            transactions.appendText("Withdrawal - balance updated.\n");
        } else if (!db.withdraw(tmp)){
            transactions.appendText("Withdrawal - insufficient funds.\n");
        }
        resetFormFromWithdrawDeposit();
    }

}



