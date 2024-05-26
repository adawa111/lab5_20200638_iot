package com.example.lab5_20200638_iot;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Task.class},version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static TaskDatabase INSTANCE;

    static TaskDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (TaskDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class,"hr")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
