package com.example.globalgrade.classes;

public class CourseClass {
    private String title;
    private double grade;
    private int image;

    public CourseClass(String title, double grade, int image) {
        this.title = title;
        this.grade = grade;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public double getGrade() {
        return grade;
    }

    public int getImageResource() {
        return image;
    }
}
