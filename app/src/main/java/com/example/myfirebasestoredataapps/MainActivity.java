package com.example.myfirebasestoredataapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText, ageEditText, genderEditText;
    Button saveButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        genderEditText = findViewById(R.id.genderEditTextId);
        saveButton = findViewById(R.id.saveButtonId);
        databaseReference = FirebaseDatabase.getInstance().getReference("student");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    public void saveData() {
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Student student = new Student(name,age,gender);
        databaseReference.child(key).setValue(student);
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();

        

    }
}