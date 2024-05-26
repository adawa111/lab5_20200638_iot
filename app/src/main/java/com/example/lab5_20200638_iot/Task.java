package com.example.lab5_20200638_iot;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity (tableName = "task")
public class Task implements Serializable {

    @PrimaryKey
    @NonNull
    private int id;
    private String title;
    private String description;
    private long dueDate;
    private int importance;

    private String codigo;

    public static final int IMPORTANCE_HIGH = 1;
    public static final int IMPORTANCE_DEFAULT = 2;
    public static final int IMPORTANCE_LOW = 3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long  getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
