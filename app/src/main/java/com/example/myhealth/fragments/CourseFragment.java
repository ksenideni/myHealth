package com.example.myhealth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.myhealth.AppDataBase;
import com.example.myhealth.Course;
import com.example.myhealth.R;

public class CourseFragment extends Fragment implements View.OnClickListener {

    Course course;

    private AppDataBase appDataBase;
    private EditText et_duration, et_medication;
    private Button addCourse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courses, container, false);

        appDataBase = AppDataBase.geAppdatabase(getActivity());
        Course course=new Course(0, 0, "");
        et_duration=v.findViewById(R.id.days_of_course);
        et_medication=v.findViewById(R.id.name_of_course);
        addCourse=v.findViewById(R.id.add_course);
        addCourse.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View v) {
        course=new Course(0, Integer.parseInt(et_duration.getText().toString()), et_medication.getText().toString());
        appDataBase.getCourseDAO().InsertCourse(course);
        JournalFragment journalFragment=new JournalFragment();
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, journalFragment).commit();
    }
}
