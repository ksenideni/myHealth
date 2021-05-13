package com.example.myhealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCheck extends RecyclerView.Adapter<AdapterCheck.CheckViewHolder> {
    private Course course;
    private AppDataBase appDataBase1;

    private int done_courses;
    public int getDone_courses() {
        return done_courses;
    }
    public void setDone_courses(int done_courses) {
        this.done_courses = done_courses;
    }

    public AdapterCheck(Course course, AppDataBase appDataBase){
        this.course=course;
        this.appDataBase1=appDataBase;
    }





    @NonNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.check_mark_item, parent, false);
        return new CheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckViewHolder holder, int position) {

        holder.checkBox.setChecked(course.getDone()>position);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    course.setDone(course.getDone()+1);
                    appDataBase1.getCourseDAO().UpdateCourse(course);
                }
                else {
                    course.setDone(course.getDone() - 1);
                    appDataBase1.getCourseDAO().UpdateCourse(course);
                }
            }
        });
        holder.days.setText(position+1 + " день");
        if(position<done_courses){
            holder.checkBox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return course.getDuration();
    }


    public class CheckViewHolder extends RecyclerView.ViewHolder{
        private TextView days;

        private CheckBox checkBox;
        public CheckBox getCheckBox() {
            return checkBox;
        }
        public CheckViewHolder(@NonNull View itemView) {
            super(itemView);
            days=itemView.findViewById(R.id.tv_days);
            checkBox=itemView.findViewById(R.id.checkBox);
        }
    }
}
