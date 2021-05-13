package com.example.myhealth;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    void InsertCourse(Course... courses);

    @Update
    void UpdateCourse(Course... courses);

    @Delete
    void DeleteCourse(Course... courses);

    @Query("Select * from course_table")
    List<Course> getCourses();
}


