/*
    Created by Daniel Grewal 100768376
    SOFE4640 - Assignment 1
    EMI Calculator App
 */

package com.example.assign1_emi;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Init objects from components in layout
        // Also init intent from main activity
        TextView textResult = findViewById(R.id.text_Result);
        Button btnShare = findViewById(R.id.btn_Share);
        Button btnBack = findViewById(R.id.btn_back);
        Button btnSearch = findViewById(R.id.btn_Search);
        Button btnCopy = findViewById(R.id.btn_Copy);
        Intent resultIntent = getIntent();

        // Get the result from main activity via intent
        // Show an error message if intent or result is null
        if (resultIntent != null) {
            String result = resultIntent.getStringExtra("emiAmount");
            if (result != null) {
                textResult.setText(result);
            }
        } else {
            textResult.setText(R.string.label_Error);

            // Set toast bar message for user
            CharSequence text = "ERROR! Please go back and try again";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }

        // On click listener for share button
        // This will trigger Android intent for 'Share'
        // e.g. Send result in an email, SMS etc.
        btnShare.setOnClickListener(view -> {
            Intent shareIntent = getIntent();
            String result = shareIntent.getStringExtra("emiAmount");
            shareResult(result);
        });

        // On click listener for search button
        // This will trigger Android intent for Google web search
        // e.g. find a mortgage broker
        btnSearch.setOnClickListener(view -> {
            Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
            searchIntent.putExtra(SearchManager.QUERY, "Mortgage broker near me");

            // Set toast bar message for user
            CharSequence text = "Starting Google web search";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

            startActivity(searchIntent);
        });

        // On click listener for copy to clipboard button
        btnCopy.setOnClickListener(view -> {
            // Declare/init clipboard objects
            ClipboardManager myClipboard;
            ClipData myClip;

            // Get EMI result via Intent and copy to clipboard
            Intent copyIntent = getIntent();
            myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            String text = copyIntent.getStringExtra("emiAmount");
            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);

            // Show toast bar message showing value is copied to clipboard
            Toast.makeText(getApplicationContext(), "EMI Copied to Clipboard",Toast.LENGTH_SHORT).show();
        });

        // On click listener for back button
        // Allows user to navigate back to main activity
        btnBack.setOnClickListener(view -> onBackPressed());
    }

    // This will trigger Android intent for 'Share'
    private void shareResult(String emiResult) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Your EMI was calculated as: " + emiResult);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Your EMI Calculation");
        startActivity(Intent.createChooser(shareIntent, "Share Your EMI Result"));
    }
}