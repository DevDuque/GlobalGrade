package com.example.globalgrade.classes;

public class CourseClass {

    private String title;
    private Double grade;
    private boolean approved;

    public CourseClass(String title, Double grade) {
        this.title = title;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public Double getGrade() {
        return grade;
    }

    public boolean getApproved() {
        return grade >= 60;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
