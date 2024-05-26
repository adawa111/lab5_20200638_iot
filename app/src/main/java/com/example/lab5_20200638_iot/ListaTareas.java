package com.example.lab5_20200638_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaTareas extends AppCompatActivity {

    String codigopucp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_tareas);

        Task[] listatask = new Task[0];

        ListatareasAdapter adapter = new ListatareasAdapter();
        adapter.setContext(ListaTareas.this);
        adapter.setTasks(Arrays.asList(listatask));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListaTareas.this));

        codigopucp = (String) getIntent().getSerializableExtra("codigo");
    }

    public void iraTareas(View view) {

        Intent intent = new Intent(this, AgregarTarea.class);
        intent.putExtra("codigo", (Serializable) codigopucp);


        startActivity(intent);
    }


}