package com.example.globalgrade;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

        // Populate the card layout for each card
        populateCardLayout(findViewById(R.id.card_one), getString(R.string.str_android), getString(R.string.str_grade_incomplete));
        populateCardLayout(findViewById(R.id.card_two), getString(R.string.str_ios), getString(R.string.str_grade_incomplete));
        populateCardLayout(findViewById(R.id.card_three), getString(R.string.str_database), getString(R.string.str_grade_incomplete));
        populateCardLayout(findViewById(R.id.card_four), getString(R.string.str_oop), getString(R.string.str_grade_incomplete));
    }

    private void populateCardLayout(CardView card, String title, String grade) {
        boolean approved = false; // Default is not approved

        CourseClass course1 = new CourseClass(title, grade, approved);

        // Get references to views in the included layout
        TextView courseTitleTextView = card.findViewById(R.id.course_title);
        TextView courseGradeTextView = card.findViewById(R.id.course_grade);
        ImageView courseImageView = card.findViewById(R.id.course_image);

        // Get data for the course to replace it
        String courseTitle = course1.getTitle();
        String courseGrade;
        if (approved) {
            courseGrade = getString(R.string.str_grade_complete) + " " + Double.parseDouble(grade); // Grade Complete
        } else {
            courseGrade = getString(R.string.str_grade_incomplete); // Grade Incomplete
        }
        int courseImageId = R.drawable.light_icon_help;

        // Set the data to the views
        courseTitleTextView.setText(courseTitle);
        courseGradeTextView.setText(courseGrade);
        courseImageView.setImageResource(courseImageId);
    }

}