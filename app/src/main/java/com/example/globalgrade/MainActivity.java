package com.example.globalgrade;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.globalgrade.classes.CourseClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Function to make the design respect the device resolutions
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Populate the card layout
        populateCardLayout(getString(R.string.str_android), 80, R.drawable.light_icon_help);
    }

    private void populateCardLayout(String title, double grade, int image) {
        CourseClass course1 = new CourseClass(title, grade, image);

        // Get references to views in the included layout
        TextView courseTitleTextView = findViewById(R.id.course_title);
        TextView courseGradeTextView = findViewById(R.id.course_grade);
        ImageView courseImageView = findViewById(R.id.course_image);

        // Get data for the course to replace it
        String courseTitle = course1.getTitle();
        String courseGrade = String.valueOf(course1.getGrade());
        int courseImageId = course1.getImageResource();

        if (course1.getGrade() >= 60.0) {
            courseImageId = R.drawable.light_icon_check;
        } else {
            courseImageId = R.drawable.light_icon_fail;
        }

        // Set the data to the views
        courseTitleTextView.setText(courseTitle);
        courseGradeTextView.setText(courseGrade);
        courseImageView.setImageResource(courseImageId);
    }

}