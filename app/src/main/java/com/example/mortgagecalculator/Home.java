package com.example.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Principal;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Sets the Title of this page to Mortgage Calculator and Sets the output Textbox to Monthly Installments
        this.setTitle("Mortgage Calculator");
        TextView montlyamount = (TextView) findViewById(R.id.editMonthlyAmount);
        montlyamount.setText("Montly Installments ");
    }
    public boolean validateInterest(View view){
        //gets the textinput from the id and checks if it is a blank string
        //if the string is not blank, then the next part is to check if the input is in range 0 to 100
        //Only when Interest rate is between 0 and 100 it validates as true
        //else it displays an Error message on the App
        final EditText EditTextIP = (EditText) findViewById(R.id.editInterestRate);
        String str = EditTextIP.getText().toString();
        System.out.println(str);

        if (str.equals("") || str.contains("\n")) {
            EditTextIP.setError("Cannot be blank");
            return false;
        }
        else {
            double inputtoDouble = Double.parseDouble(str);

            if (inputtoDouble > 0 && inputtoDouble < 100) {

                //Show number
                System.out.println(inputtoDouble);
                EditTextIP.setError(null);
                return true;

            } else {
                //Clear text
                EditTextIP.setText("");
                //Show Error
                EditTextIP.setError("Number must be between 0-100");
                return false;
            }
        }
    }

    public boolean validateMortgage(View view){
        //gets the textinput from the Amortization Textbox and checks if it is a blank string
        //if the string is not blank, then the next part is to check if the input is in range 0 to 30
        //Only when Interest rate is between 0 and 30 it validates as true
        //else it displays an Error message on the App
        final EditText EditTextIP = (EditText) findViewById(R.id.editAmortizationpeiord);
        String str = EditTextIP.getText().toString();
        System.out.println(str);

        if (str.equals("") || str.contains("\n")) {
            EditTextIP.setError("Cannot be blank");
            return false;

        }
        else {
            int inputtoDouble = Integer.parseInt(str);

            if (inputtoDouble > 0 && inputtoDouble <= 30) {

                //Show number
                System.out.println(inputtoDouble);
                EditTextIP.setError(null);
                return true;

            } else {
                //Clear text
                EditTextIP.setText("");
                //Show Error
                EditTextIP.setError("Value must be between 1-30");
                return false;
            }
        }
    }
    //runs when user clicks the calculate button
    public void validateAllFields(View view){
        //validates the mortgage and interest are valid input and proceeds to calculate the monthly payments
        //A lot of casting is done to get the input from Textfield to string to a double
        //value for all input is extracted and sent to the calculateMonthly Payment function
        boolean isMortgageValid = validateMortgage(view);
        boolean isInterestValid = validateInterest(view);

        if(isMortgageValid && isInterestValid){
            final EditText EditTextIP = (EditText) findViewById(R.id.editAmortizationpeiord);
            String AmortAmount = EditTextIP.getText().toString();
            double amortAmout = Double.parseDouble(AmortAmount);

            final EditText InterestInput = (EditText) findViewById(R.id.editInterestRate);
            String InterestAmount = InterestInput.getText().toString();
            double interestRate = Double.parseDouble(InterestAmount);

            final EditText Priciple = (EditText) findViewById(R.id.editMortgagePrinciple);
            String PricipleAmount = Priciple.getText().toString();
            double presentValue = Double.parseDouble(PricipleAmount);

            System.out.println("Everything is good to go");

            //following function passes all the required inputs to calculate monthly payments
            CalculateMontlyPayments(presentValue, interestRate, amortAmout, view);


        }
        else{
            System.out.printf("something is not right");
        }

    }

    public void CalculateMontlyPayments(double Principle, double interestRate, double years, View view){

        //yearly interestrate needs to be converted into montly interestRate
        double interest = interestRate/100/12;
        double months = years*12;
        double num = Principle*interest*(Math.pow((1+interest),months));
        double denominator = (Math.pow((1+interest),months))-1;
        Double montly = num/denominator;

        //rounds the decimal to 10th decimal place
        Double roundedmonthly = (double) (Math.round(montly*10.0)/10.0);
        System.out.println(num);
        System.out.println(denominator);
        System.out.println(montly);

        //Converts the decimal to a string and ouputs it on the TextView
        String output = roundedmonthly.toString();
        TextView montlyamount = (TextView) findViewById(R.id.editMonthlyAmount);
        montlyamount.setText("Monthly Amount: $" + output);

    }
    //function is called when user clicks the clear button.
    public void clearinput(View view){

        //clears the input fields.
        final EditText EditTextIP = (EditText) findViewById(R.id.editAmortizationpeiord);
        EditTextIP.getText().clear();

        final EditText InterestInput = (EditText) findViewById(R.id.editInterestRate);
        InterestInput.getText().clear();

        final EditText Priciple = (EditText) findViewById(R.id.editMortgagePrinciple);
        Priciple.getText().clear();
    }
}