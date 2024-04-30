package com.example.globalgrade;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgrade.classes.CardAdapter;
import com.example.globalgrade.classes.CourseClass;

import java.util.ArrayList;
import java.util.List;


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

        // Find the RecyclerView by its ID
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        String android = String.valueOf(R.string.str_android);

        // Create a list of CourseClass objects
        List<CourseClass> courseList = new ArrayList<>();
        courseList.add(new CourseClass(getString(R.string.str_android), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_ios), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_database), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_oop), getString(R.string.str_grade_incomplete)));

        // Create an instance of CardAdapter and pass the list of CourseClass objects to it
        CardAdapter adapter = new CardAdapter(courseList);

        // Set the adapter on your RecyclerView
        recyclerView.setAdapter(adapter);

        // Set a layout manager on your RecyclerView (e.g., LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}