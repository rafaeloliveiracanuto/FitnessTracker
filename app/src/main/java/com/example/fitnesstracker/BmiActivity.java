package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

                int height = Integer.parseInt(sHeight);
                int weight = Integer.parseInt(sWeight);

                double result = calculateBMI(height, weight);


            }
        });



    }

    private int bmiResponse(double bmi) {

    }

    private boolean validate() {
        return (!editHeight.getText().toString().startsWith("0")
         && !editWeight.getText().toString().startsWith("0")
            && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty());
    }

    private double calculateBMI(int height, int weight) {
        return weight / ( ((double) height / 100) ) * ( ((double) height / 100) );
    }
}