package com.example.myhealth;

public class Course {
    //длительность курса
    private int duration;
    //название препарата
    private String medication;

    public Course(int duration, String medication){
        this.duration=duration;
        this.medication=medication;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}