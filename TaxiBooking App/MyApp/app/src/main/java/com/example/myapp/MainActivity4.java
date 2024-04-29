package com.example.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    Button appybutton;

    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        home = (ImageView) findViewById(R.id.homebtnId);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this,MainActivity.class);
                startActivity(intent);
            }
        });

        radioGroup = findViewById(R.id.radio_group);
        textView = findViewById(R.id.selectionbox);
        appybutton = findViewById(R.id.radiobtnId);


        appybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity4.this, "PAYMENT SUCCESSFULL & CONFIRM ORDER !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        textView.setText("You selected :" + radioButton.getText());
        textView.setTextColor(Color.GREEN);
    }
}