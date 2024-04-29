package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {
    ImageView nextbtn2;
    TextView t1,t2;
    EditText E1,E2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        nextbtn2 = (ImageView) findViewById(R.id.NextBtnId2);
        t1=(TextView)findViewById(R.id.t1) ;
        t2=(TextView)findViewById(R.id.t2) ;
        E1=(EditText) findViewById(R.id.editTextFrom) ;
        E2=(EditText) findViewById(R.id.editTextTo) ;

        nextbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this,MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}