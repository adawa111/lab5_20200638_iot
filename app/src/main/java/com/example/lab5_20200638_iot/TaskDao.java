package com.example.lab5_20200638_iot;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TaskDao {

    @Insert
    public void insert(Task task);
    @Update
    public void update(Task task);


    @Query("select * from task")
    public List<Task> listarTask();

    @Query("select * from task where codigo = :code")
    public List<Task> listarTask(String code);

    @Query("SELECT * FROM task WHERE id = :aidi")
    public Task encontrarTask(int aidi);

    @Delete
    void deleteTask (Task task);

}
