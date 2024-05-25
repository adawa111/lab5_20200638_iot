package com.example.lab5_20200638_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    private EditText editTextText;

    public void iraTareas(View view) {

        editTextText = findViewById(R.id.editTextText);

        String codigo =  editTextText.getText().toString().trim();
        Intent intent = new Intent(this, ListaTareas.class);
        intent.putExtra("listanumeros", (Serializable) codigo);


        startActivity(intent);
    }
}