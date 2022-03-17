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
    private RadioButton acctType, loyal, nb, camden, newark;
    @FXML
    private RadioButton moneymarket, savings, collegechecking, checkings;
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
        //if(acctType.equals("checkings")) {
        if (checkings.isSelected()) {
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
        } else


            //if(acctType.equals("savings")) {
            if (savings.isSelected()) {
                boolean isLoyal = false;
                loyal.setDisable(false);
                if (loyal.isSelected()) {
                    isLoyal = true;
                }

                Savings s = new Savings(profile, totalAmount, false, isLoyal);
                find = db.findDupe(s);
                if (db.findClosedProfile(s)) {
                    db.open(s);
                    information.appendText("Account opened \n");
                } else if (find == FOUND_DUPE) {
                    information.appendText(profile.getfName() + " " + profile.getlName()
                            + " " + profile.getDob() + " same account(" + s.getType() + ") is in the database.");
                } else {
                    db.open(s);
                    information.appendText("Account opened \n");
                }
                } else


                    //if(acctType.equals("moneymarket")) {
                    if (moneymarket.isSelected()) {
                        nb.setDisable(false);
                        camden.setDisable(false);
                        newark.setDisable(false);
                        if (totalAmount < MIN_BAL) {
                            information.appendText("Minimum of $2500 to open a Money Market account.");
                            return;
                        }
                        MoneyMarket mm = new MoneyMarket(profile, totalAmount, false, true);
                        find = db.findDupe(mm);
                        if (db.findClosedProfile(mm)) {
                            db.open(mm);
                            information.appendText("Account opened \n");
                        } else if (find == FOUND_DUPE) {
                            information.appendText(profile.getfName() + " " + profile.getlName()
                                    + " " + profile.getDob() + " same account(" + mm.getType() + ") is in the database.");
                        } else {
                            db.open(mm);
                            information.appendText("Account opened \n");
                        }

                    } else


                        //if(acctType.equals("collegechecking")) {
                        if (collegechecking.isSelected()) {
                            int loc = 0;
                            if (nb.isSelected()) {
                                loc = 0;
                            } else if (camden.isSelected()) {
                                loc = 1;
                            } else if (newark.isSelected()) {
                                loc = 2;
                            }

                            CollegeChecking cc = new CollegeChecking(profile, totalAmount, false, loc);

                            find = db.findDupe(cc);

                            if (db.findClosedProfile(cc)) {
                                db.open(cc);
                                information.appendText("Account reopened.");
                            } else if (find == FOUND_DUPE) {
                                information.appendText(profile.getfName() + " " + profile.getlName()
                                        + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");
                            } else if (!db.open(cc)) {
                                System.out.println(profile.getfName() + " " + profile.getlName()
                                        + " " + profile.getDob() + " same account(" + cc.getType() + ") is in database.");

                            } else {
                                db.open(cc);
                                information.appendText("Account opened.");
                            }
                        }

                //clear fields
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
}



