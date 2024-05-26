package com.example.lab5_20200638_iot;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AgregarTarea extends AppCompatActivity {



    private EditText editTextTitulo, editTextDescripcion;
    private TextView textViewDateTime;
    private Calendar calendar;

    private TaskRepository taskRepository;

    String codigopucp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_tarea);


        codigopucp = (String) getIntent().getSerializableExtra("codigo");

        textViewDateTime = findViewById(R.id.textView3);
        Button buttonPickDateTime = findViewById(R.id.btn_pick_date_time);


        editTextTitulo = findViewById(R.id.editTitulo);
        editTextDescripcion = findViewById(R.id.editDescription);

        Button buttonGuardar = findViewById(R.id.botonGuardar);
        calendar = Calendar.getInstance();

        taskRepository = new TaskRepository(getApplication());
        buttonPickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTime();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTask();
            }
        });
    }

    private void pickDateTime() {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AgregarTarea.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        updateDateTimeDisplay();
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void updateDateTimeDisplay() {
        String selectedDateTime = String.format("%02d/%02d/%04d %02d:%02d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE));
        textViewDateTime.setText(selectedDateTime);
    }

    private void guardarTask() {
        String titulo = editTextTitulo.getText().toString().trim();
        String descripcion = editTextDescripcion.getText().toString().trim();
        long fecha = calendar.getTimeInMillis();

        if (!titulo.isEmpty() && !descripcion.isEmpty() && fecha > 0) {
            Task task = new Task();
            task.setTitle(titulo);
            task.setDescription(descripcion);
            task.setDueDate(fecha);
            task.setCodigo(codigopucp);

            taskRepository.insertarTask(new Task[]{task});
            //setNotification(task);
            finish();
        } else {
            showToast("Ocurrió un error al guardar la tarea y el recordatorio");
        }
    }

    private void setNotification(Task task) {
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("task_title", task.getTitle());
        intent.putExtra("task_id", task.getId());
        intent.putExtra("task_time", task.getDueDate());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, task.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getDueDate(), pendingIntent);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}