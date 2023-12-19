package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeight, editTextWidth, editTextPaintConsumption, editTextLayers, editTextCanVolume;
    private RadioGroup radioGroupReserve;
    private RadioButton radioButton10Percent, radioButton15Percent, radioButtonNoReserve;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWidth = findViewById(R.id.editTextWidth);
        editTextPaintConsumption = findViewById(R.id.editTextPaintConsumption);
        editTextLayers = findViewById(R.id.editTextLayers);
        editTextCanVolume = findViewById(R.id.editTextCanVolume);

        radioGroupReserve = findViewById(R.id.radioGroupReserve);
        radioButton10Percent = findViewById(R.id.radioButton10Percent);
        radioButton15Percent = findViewById(R.id.radioButton15Percent);
        radioButtonNoReserve = findViewById(R.id.radioButtonNoReserve);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePaint();
            }
        });
    }

    private void calculatePaint() {
        double height = Double.parseDouble(editTextHeight.getText().toString());
        double width = Double.parseDouble(editTextWidth.getText().toString());
        double paintConsumption = Double.parseDouble(editTextPaintConsumption.getText().toString());
        int layers = Integer.parseInt(editTextLayers.getText().toString());
        double canVolume = Double.parseDouble(editTextCanVolume.getText().toString());

        double totalArea = height * width;
        double totalPaintRequired = paintConsumption * totalArea * layers;
        double reserveFactor = getReserveFactor();

        double totalVolumeWithReserve = totalPaintRequired * reserveFactor;

        if (totalVolumeWithReserve > canVolume) {
            textViewResult.setText("Не хватит краски. Увеличьте объем банки.");
        } else {
            totalVolumeWithReserve = Math.round(totalVolumeWithReserve * 10.0) / 10.0;
            textViewResult.setText("Итоговый расход: " + totalVolumeWithReserve + " литров краски");
        }
    }


    private double getReserveFactor() {
        int selectedReserveId = radioGroupReserve.getCheckedRadioButtonId();

        if (selectedReserveId == R.id.radioButton10Percent) {
            return 1.10;
        } else if (selectedReserveId == R.id.radioButton15Percent) {
            return 1.15;
        } else if (selectedReserveId == R.id.radioButtonNoReserve) {
            return 1.0;
        } else {
            return 1.0;
        }
    }
}