package com.example.myhealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.CourseViewHolder> {

    private  AppDataBase appDataBase;

    private ArrayList<Course> allCourses;
    //поля для лекарстава и длительности приема


    public ArrayList<Course> getAllCourses() {
        return allCourses;
    }

    public AdapterCourse(ArrayList<Course> courses, AppDataBase appDataBase){
        this.allCourses=courses;
        this.appDataBase=appDataBase;
    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(v);
    }

    //done логика добавления
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {


        Course course = allCourses.get(position);

        holder.medication.setText(course.getMedication());
        //holder.duration.setText(course.getDuration()+"дней");

        GridLayoutManager gridLayoutManager= new GridLayoutManager(holder.itemView.getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        holder.rvCheckBox.setLayoutManager(gridLayoutManager);

//        GridLayoutManager gridLayout=new GridLayoutManager((Activity)holder.itemView.getContext(), course.getDuration());
        holder.rvCheckBox.setLayoutManager(gridLayoutManager);
        AdapterCheck adapterCheck=new AdapterCheck(course, appDataBase);
        adapterCheck.setDone_courses(course.getDone());
        holder.rvCheckBox.setAdapter(adapterCheck);
    }

    @Override
    public int getItemCount() {
        return allCourses.size();
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView medication;

        //private TextView duration;
        //ресайклер галочек
        private RecyclerView rvCheckBox;
        //  private AdapterCheck adapterCheck;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            medication=itemView.findViewById(R.id.tv_medication);
            //duration=itemView.findViewById(R.id.tv_duration);
            rvCheckBox=itemView.findViewById(R.id.rv_check_mark);

        }
    }
}
