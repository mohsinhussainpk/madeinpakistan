package com.example.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TermsAndConditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.termsandconditions);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            bufferedReader.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.termsandconditionTextView);
        textView.setText(stringBuilder.toString());
    }
}
