package com.example.myhealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCheck extends RecyclerView.Adapter<AdapterCheck.CheckViewHolder> {
    private Course course;

    public AdapterCheck(Course course){
        this.course=course;
    }



    @NonNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.check_mark_item, parent, false);
        return new CheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckViewHolder holder, int position) {
        holder.days.setText(position+1 + "день");
    }

    @Override
    public int getItemCount() {
        return course.getDuration();
    }


    public class CheckViewHolder extends RecyclerView.ViewHolder{
        private TextView days;
        public CheckViewHolder(@NonNull View itemView) {
            super(itemView);
            days=itemView.findViewById(R.id.tv_days);
        }
    }
}
