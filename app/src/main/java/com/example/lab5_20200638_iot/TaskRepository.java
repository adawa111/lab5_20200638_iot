package com.example.lab5_20200638_iot;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TaskRepository extends ViewModel {

    private TaskDao taskDao;

    private MutableLiveData<List<Task>> listaTasks
            = new MutableLiveData<>();

    public MutableLiveData<List<Task>> getListaTasks(){
        return listaTasks;
    }

    public void setListaTasks(MutableLiveData<List<Task>> listaTasks){}
    public TaskRepository(Application application){
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        taskDao = taskDatabase.taskDao();
    }
    public void insertarTask(Task[] arregloTask){
        GuardarThread guardarThread = new GuardarThread();
        guardarThread.execute(arregloTask);
    }
        class GuardarThread extends AsyncTask<Task,Void,Void> {
            @Override
            protected Void doInBackground(Task... tasks) {
                for (int i = 0; i < tasks.length; i++) {
                    taskDao.insert(tasks[i]);
                }
                return null;
            }
        }
        public void borrarTrabajo(Task task){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    taskDao.deleteTask(task);
                }
            });
            t.start();
        }

        public void obtenerTask(int ad){
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    taskDao.encontrarTask(ad);
                }
            });
            a.start();

        }

    }

