package com.example.lab5_20200638_iot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListatareasAdapter extends RecyclerView.Adapter<ListatareasAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private Context context;


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        Task task;
        public TaskViewHolder (@NonNull View itemView){
            super(itemView);
            Button buttonEditar = itemView.findViewById(R.id.button3);
            Button buttonEliminar = itemView.findViewById(R.id.button4);
            buttonEditar.setOnClickListener(view ->{
                Integer id = task.getId();
                String cod = task.getCodigo();
                Log.d("msg-test","Presionando la tarea con id: "+id);
            });
            buttonEliminar.setOnClickListener(view ->{
                Integer id = task.getId();
                String cod = task.getCodigo();
                Log.d("msg-test","Presionando la tarea con id: "+id);
            });
        }
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task t  = tasks.get(position);
        holder.task = t;

        TextView textViewtitulo = holder.itemView.findViewById(R.id.task_title);
        textViewtitulo.setText(t.getTitle());
        TextView textViewDescription = holder.itemView.findViewById(R.id.task_description);
        textViewtitulo.setText(t.getDescription());
        TextView textViewdueDate = holder.itemView.findViewById(R.id.task_due_date);
        String date = String.valueOf(t.getDueDate());
        textViewtitulo.setText(date);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
