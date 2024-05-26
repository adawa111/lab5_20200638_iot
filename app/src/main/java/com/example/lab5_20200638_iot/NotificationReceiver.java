package com.example.lab5_20200638_iot;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "task_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String taskTitle = intent.getStringExtra("task_title");
        long taskTime = intent.getLongExtra("task_time", 0);

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        long currentTime = System.currentTimeMillis();
        if (taskTime - currentTime <= 3 * 60 * 60 * 1000) { // Dentro de las próximas 3 horas
            importance = NotificationManager.IMPORTANCE_HIGH;
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Task Reminder", importance);
            notificationManager.createNotificationChannel(channel);
        }

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Recordatorio de Tarea")
                .setContentText(taskTitle)
                .setPriority(importance)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify((int) taskTime, builder.build());
    }
}
