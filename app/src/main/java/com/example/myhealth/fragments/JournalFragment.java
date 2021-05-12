package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealth.AdapterCourse;
import com.example.myhealth.Course;
import com.example.myhealth.R;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class JournalFragment extends Fragment {

    private RecyclerView rvCourses;
    private AdapterCourse adapterCourse;
    private ArrayList<Course> allCourses=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_journal, container, false);
        rvCourses=v.findViewById(R.id.rv_courses);
        for(int i=0; i<10; i++){
            Course course=new Course(i+3, "name"+i);
            allCourses.add(i, course );
        }

        initRecyclerView();
        return v;
    }

    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rvCourses.setLayoutManager(linearLayoutManager);
        adapterCourse=new AdapterCourse(allCourses);
        rvCourses.setAdapter(adapterCourse);

    }
}