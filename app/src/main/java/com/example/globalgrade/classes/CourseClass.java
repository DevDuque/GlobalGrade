package com.example.globalgrade.classes;

public class CourseClass {
    private String title;
    private String grade;
    private boolean approved;

    public CourseClass(String title, String grade, boolean approved) {
        this.title = title;
        this.grade = grade;
        this.approved = approved;
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
}
