package com.example.myhealth;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.CourseViewHolder> {

    private ArrayList<Course> allCourses;
    //поля для лекарстава и длительности приема


    public AdapterCourse(ArrayList<Course> courses){
        this.allCourses=courses;
    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(v);
    }

    //todo логика добавления
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = allCourses.get(position);
        holder.medication.setText(course.getMedication());
        holder.duration.setText(course.getDuration()+"дней");

        GridLayoutManager gridLayout=new GridLayoutManager((Activity)holder.itemView.getContext(), course.getDuration());
        holder.rvCheckBox.setLayoutManager(gridLayout);
        AdapterCheck adapterCheck=new AdapterCheck(course);
        holder.rvCheckBox.setAdapter(adapterCheck);


    }

    @Override
    public int getItemCount() {
        return allCourses.size();
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView medication, duration;
        //ресайклер галочек и адаптер галочек
        private RecyclerView rvCheckBox;
        //  private AdapterCheck adapterCheck;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            medication=itemView.findViewById(R.id.tv_medication);
            duration=itemView.findViewById(R.id.tv_duration);
            rvCheckBox=itemView.findViewById(R.id.rv_check_mark);

        }
    }
}
