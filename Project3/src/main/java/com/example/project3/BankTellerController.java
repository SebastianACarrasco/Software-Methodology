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
    private static final int IS_LOYAL = 1;
    private static final int MIN_BALANCE = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private static final int FOUND_DUPE = 1;
    private static final int NO_DUPE = 0;
    private int find;
    private String firstName, lastName;
    private Profile profile;
    private double totalAmount;
    private Date date;
    public AccountDatabase db = new AccountDatabase();
    @FXML
    private RadioButton acctType;
    @FXML
    private TextField fn, ln, amount;
    @FXML
    private TextArea information;
    @FXML
    private Button open, close;
    @FXML
    private DatePicker dob;


    @FXML
    public void getFirstName(ActionEvent event) {
        this.firstName = fn.getText();
    }

    @FXML
    public void getLastName(ActionEvent event) {
        this.lastName = ln.getText();
    }

    @FXML
    public void getDOB(ActionEvent event) {
        //yyyy-mm-dd
        StringTokenizer st = new StringTokenizer(dob.getValue().toString(), "-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        this.date = new Date(month+"/"+day+"/"+year);
    }

    @FXML
    public void getAmount(ActionEvent event) {

        this.totalAmount = Double.parseDouble(amount.getText());
        try {
            if (totalAmount < MIN_BALANCE) {
                information.appendText("Initial deposit cannot be 0 or negative \n");
                return;
            }
        } catch (NumberFormatException e) {
            information.appendText("Not a valid amount \n");
            return;
        }
    }

    @FXML
    public void openAccount(ActionEvent event) {

        this.profile = new Profile(firstName, lastName, date);
        Checking c = new Checking(profile, totalAmount, false);
        find = db.findDupe(c);
        if (db.findClosedProfile(c)) {
            db.open(c);
            information.appendText("Account opened \n");
        } else if (find == FOUND_DUPE) {
            information.appendText(profile.getfName() + " " + profile.getlName()
                    + " " + profile.getDob() + " same account(" + c.getType() + ") is in the database.");
        } else {
            db.open(c);
            information.appendText("Account opened \n");
        }
    }
}



