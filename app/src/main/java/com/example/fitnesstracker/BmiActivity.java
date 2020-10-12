package com.example.fitnesstracker;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BmiActivity extends AppCompatActivity {

    private EditText editWeight;
    private EditText editHeight;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        editWeight = findViewById(R.id.editText_bmi_weight);
        editHeight = findViewById(R.id.editText_bmi_height);
        buttonSend = findViewById(R.id.button_bmi_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    Toast.makeText(BmiActivity.this, R.string.fields_errors, Toast.LENGTH_LONG).show();
                    return;
                }

                String sHeight = editHeight.getText().toString();
                String sWeight = editWeight.getText().toString();

                double height = Double.parseDouble(sHeight);
                double weight = Double.parseDouble(sWeight);

                double result = calculateBMI(height, weight);
                Log.d("Testing", String.valueOf(result));

                int bmiResponseId = bmiResponse(result);

                Toast.makeText(BmiActivity.this, bmiResponseId, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @StringRes
    private int bmiResponse(double bmi) {
        if (bmi < 15)
            return R.string.bmi_severely_low_weight;
        else if (bmi < 16)
            return R.string.bmi_very_low_weight;
        else if (bmi < 18.5)
            return R.string.bmi_low_weight;
        else if (bmi < 25)
            return R.string.normal;
        else if (bmi < 30)
            return R.string.bmi_high_weight;
        else if (bmi < 35)
            return R.string.bmi_so_high_weight;
        else if (bmi < 40)
            return R.string.bmi_severely_high_weight;
        else
            return R.string.bmi_extreme_weight;
    }

    private boolean validate() {
        return (!editHeight.getText().toString().startsWith("0")
         && !editWeight.getText().toString().startsWith("0")
            && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty());
    }

    private double calculateBMI(double height, double weight) {
        return weight / ((height / 100) * (height / 100));
    }
}