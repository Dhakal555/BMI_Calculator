package com.example.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText txtWeight, txtHeight;
    TextView txtResult, txtInfo;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // making application name disappear
        requestWindowFeature(Window.FEATURE_NO_TITLE); // helps to hide title
        getSupportActionBar().hide(); // hides title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // enables full screen

        setContentView(R.layout.activity_main);

        txtWeight = findViewById(R.id.weight);
        txtHeight = findViewById(R.id.height);
        txtResult = findViewById(R.id.txtRes);
        txtInfo = findViewById(R.id.txtInfo);
        btnCalculate = findViewById(R.id.btnCalculate);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();

            }
        });


    }

    public void calculateBMI(){
        DecimalFormat df = new DecimalFormat("#.00");

        String txtWeightstr = txtWeight.getText().toString();
        String txtHeightstr = txtHeight.getText().toString();

        if (txtWeightstr != null && !"".equals(txtWeight) && txtHeightstr != null && !"".equals(txtHeight)){

            float txtWeightflo = Float.parseFloat(txtWeightstr);
            float txtHeightflo = Float.parseFloat(txtHeightstr);

            float resBMI = txtWeightflo / (txtHeightflo*txtHeightflo);
            txtInfo.setText("");

            ShowBMI(Float.parseFloat(df.format(resBMI)));
        }

    }

    public void ShowBMI(float resBMI){
        String lev="";

        if(resBMI < 18.5){
            lev = getString(R.string.uw);
        }
        else if(resBMI >= 18.5 && resBMI < 25){
            lev = getString(R.string.n);
        }
        else{
            lev = getString(R.string.ow);
        }

        lev = resBMI + "\n\n" +lev;
        txtResult.setText(lev);

    }

    public void Validate(){
        // validation for empty fields
        String txtWeightstr = txtWeight.getText().toString().trim();
        String txtHeightstr = txtHeight.getText().toString().trim();

        if(txtHeightstr.matches("") || txtWeightstr.matches("")){
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
            return;
        }else {
            calculateBMI();
        }
    }

}

