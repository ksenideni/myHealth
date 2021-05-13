package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealth.AdapterCourse;
import com.example.myhealth.AppDataBase;
import com.example.myhealth.Course;
import com.example.myhealth.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class JournalFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fb;
    private AppDataBase appDataBase;
    private RecyclerView rvCourses;
    private AdapterCourse adapterCourse;
    private ArrayList<Course> allCourses=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        appDataBase = AppDataBase.geAppdatabase(getActivity());
        View v = inflater.inflate(R.layout.fragment_journal, container, false);
        rvCourses=v.findViewById(R.id.rv_courses);
        fb=v.findViewById(R.id.fb_journal);
                fb.setOnClickListener(this);
//        for(int i=0; i<2; i++){
//            Course course=new Course(2, i+7, "name"+i );
//
//            appDataBase.getCourseDAO().InsertCourse(course);
//        }

        //allCourses.addAll(appDataBase.getCourseDAO().getCourses());
        //initRecyclerView();
        allCourses.addAll(appDataBase.getCourseDAO().getCourses());
        initRecyclerView();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT){
                    Course course=((AdapterCourse)rvCourses.getAdapter()).getAllCourses().get(viewHolder.getAdapterPosition());
                    appDataBase.getCourseDAO().DeleteCourse(course);
                }
            }

        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rvCourses);
        return v;
    }

    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rvCourses.setLayoutManager(linearLayoutManager);
        adapterCourse=new AdapterCourse(allCourses, appDataBase);
        rvCourses.setAdapter(adapterCourse);

    }

    @Override
    public void onClick(View v) {
        CourseFragment courseFragment=new CourseFragment();
        Bundle bundle = new Bundle();
        courseFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, courseFragment).addToBackStack(null).commit();
    }
}