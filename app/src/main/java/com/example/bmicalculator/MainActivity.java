package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtResult = findViewById(R.id.result);
        EditText edtWeight = findViewById(R.id.edt_weight);
        EditText edtFtHeight = findViewById(R.id.edt_height_ft);
        EditText edtInHeight = findViewById(R.id.edt_height_inc);
        Button btnSubmit = findViewById(R.id.btn_calculate_submit);
        LinearLayout llMain = findViewById(R.id.llMain);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int weight = Integer.parseInt(edtWeight.getText().toString());
                    int ftHeight = Integer.parseInt(edtFtHeight.getText().toString());
                    int inHeight = Integer.parseInt(edtInHeight.getText().toString());
                    float heightInMtr = ((ftHeight*12 + inHeight) * 2.54f) / 100;
                    if(heightInMtr == 0) {
                        txtResult.setText("invalid input: height cannot be zero");
                    } else {
                        double bmi = weight / (heightInMtr * heightInMtr);
                        Log.d("BMI Calculation", "Weight: " + weight + " kg, Height: " + ftHeight + "'" + inHeight + "\", BMI: " + bmi);
                        if (bmi < 18.5) {
                            llMain.setBackgroundColor(Color.YELLOW);
                            txtResult.setText("Underweight");
                        } else if (bmi < 25) {
                            llMain.setBackgroundColor(Color.GREEN);
                            txtResult.setText("Normal weight");
                        } else if (bmi < 30) {
                            llMain.setBackgroundColor(Color.RED);
                            txtResult.setText("Overweight");
                        } else {
                            llMain.setBackgroundColor(Color.RED);
                            txtResult.setText("Obese");
                        }
                    }
                } catch (NumberFormatException e) {
                    txtResult.setText("invalid input: please enter valid numbers");
                }
            }
        });
    }
}