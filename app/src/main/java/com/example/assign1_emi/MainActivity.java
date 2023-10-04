/*
    Created by Daniel Grewal 100768376
    SOFE4640 - Assignment 1
    EMI Calculator App
 */

package com.example.assign1_emi;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    // Declare objects that will be used in all class methods
    private EditText inputAmount, inputMonths, inputInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init objects from component resources
        // These will be used to get the values from the UI
        inputAmount = findViewById(R.id.text_Result);
        inputMonths = findViewById(R.id.input_Months);
        inputInterest = findViewById(R.id.input_Interest);

        Button btnCalculate = findViewById(R.id.btn_Calculate);
        Button btnReset = findViewById(R.id.btn_Reset);
        FloatingActionButton btnHelp = findViewById(R.id.btn_Help);

        // On click listener for calculate button
        btnCalculate.setOnClickListener(view -> {
            calcEMI();

            // Set toast bar message for user
            CharSequence text = "Calculation done!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        });

        // On click listener for reset button
        btnReset.setOnClickListener(view -> {
            // Clear the user input fields
            inputAmount.getText().clear();
            inputMonths.getText().clear();
            inputInterest.getText().clear();

            // Set toast bar message for user
            CharSequence text = "Cleared all values";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        });

        // On click listener for help button
        btnHelp.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "what is equated monthly installment");

            // Set toast bar message for user
            CharSequence text = "Starting Google web search";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

            startActivity(intent);
        });

    }

    private void calcEMI() {
        try {
            double principalAmount = Double.parseDouble(inputAmount.getText().toString());
            int numMonths = Integer.parseInt(inputMonths.getText().toString());
            double annualRate = Double.parseDouble(inputInterest.getText().toString());

            // Convert annual interest rate to a monthly interest rate for the EMI formula
            double monthlyRate = (annualRate / 100) / 12;

            // Calculate equated monthly installment amount
            double emiAmount = principalAmount * monthlyRate * Math.pow(1 + monthlyRate, numMonths) / (Math.pow(1 + monthlyRate, numMonths) - 1);
            String result = "$" + String.format("%.2f", emiAmount);

            // Pass result to the result activity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("emiAmount", result);
            startActivity(intent);
        } catch (NumberFormatException e) {
            // Set toast bar message for user
            CharSequence text = "Invalid input! Please try again";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
    }
}