package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
private EditText name;
private EditText age;

private Button savebtn, viewbtn,deletebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.nameId);
        age = findViewById(R.id.ageId);
        savebtn = findViewById(R.id.savebtn);
        viewbtn = findViewById(R.id.viewbtnId);
        deletebtn = findViewById(R.id.deletebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Age = age.getText().toString();
                addToDB(Name, Age);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                finish();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Age = age.getText().toString().trim();
                deleteFromDB(Name, Age);
            }
        });

    }

    private void addToDB(String Name, String Age){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        dataHashMap.put("name", Name);
        dataHashMap.put("age", Age);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        String key = myRef.push().getKey();

        myRef.child(key).setValue(dataHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "Successful", Toast.LENGTH_SHORT).show();
                    name.getText().clear();
                    age.getText().clear();
                } else {
                    Toast.makeText(MainActivity2.this, "Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void deleteFromDB(String name, String age) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        Query query = usersRef.orderByChild("name").equalTo(name).limitToFirst(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childSnapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity2.this, "Successfully deleted", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity2.this, "Failed to delete data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    return;
                }

                Toast.makeText(MainActivity2.this, "No data found to delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(MainActivity2.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}