package com.example.globalgrade.classes;

public class CourseClass {

    private String title;
    private String grade;
    private boolean approved;

    public CourseClass(String title, String grade) {
        this.title = title;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public String getGrade() {
        return grade;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
