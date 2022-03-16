package com.example.project3;
/**
 * This is the controller class for the BankTeller. Here we deal with program
 * handling and user input from GUI.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BankTellerController {
    private static final int NEW_BRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int Camden = 2;
    private static final int IS_LOYAL = 1;
    private static final int MIN_BALANCE = 1;
    private static final int CAMDEN = 2;
    private static final int MIN_BAL = 2500;
    private Profile profile;
    private Date dob;
    public AccountDatabase db = new AccountDatabase();



    @FXML
    protected void getFirstName(ActionEvent event) {

    }

    @FXML
    protected void getLastName(ActionEvent event) {

    }

    @FXML
    protected void getDOB(ActionEvent event) {

    }

    @FXML
    protected void getAmount(ActionEvent event) {

    }

    @FXML
    protected void openAccount(ActionEvent event) {

    }

    @FXML
    protected void closeAccount(ActionEvent event) {

    }
}



