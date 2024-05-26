package com.example.lab5_20200638_iot;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        String channelId = "channelDefaultPri";

    }

    public void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelDefaultPri",
                    "Canal notificaciones default",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Canal para notificaciones con prioridad default");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            askPermission();
        }
    }
/*
    public void lanzarNotificacion () {
        Intent intent = new Intent(this, MainActivity4. class);
        PendingIntent pendingIntent = PendingIntent. getActivity(this, 0, intent,
                PendingIntent. FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, channelId )
                .setSmallIcon(R.drawable. baseline_3p_24)
                .setContentTitle( "Mi primera notificación" )
                .setContentText( "Esta es mi primera notificación en Android :D" )
                .setPriority(NotificationCompat. PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel( true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat. from(this);
        if (ActivityCompat. checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager. PERMISSION_GRANTED)
        {
            notificationManager.notify( 1, builder.build()) ;
        }
    }*/

    public void askPermission(){
//android.os.Build.VERSION_CODES.TIRAMISU == 33
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) ==
                        PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{POST_NOTIFICATIONS},
                    101);
        }
    }

    private EditText editTextText;

    public void iraTareas(View view) {

        editTextText = findViewById(R.id.editTextText);

        String codigo =  editTextText.getText().toString().trim();
        Intent intent = new Intent(this, ListaTareas.class);
        intent.putExtra("codigo", (Serializable) codigo);


        startActivity(intent);
    }
}