package com.example.globalgrade;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

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

    private RecyclerView recyclerView;
    private Spinner spinner;

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

        InitSpinner();
        InitRecycleView();

        recyclerView.setOnClickListener(onCardClicked());
    }

    public View.OnClickListener onCardClicked ()  {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make instance to next page
                Intent nextPage = new Intent(getBaseContext(), M3_Android_Grade.class);

                // Start the activity of the next page
                startActivity(nextPage);
            }
        };
    }

    public void InitSpinner() {

    }

    public void InitRecycleView() {
        // Find the RecyclerView by its ID
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Create a list of CourseClass objects
        List<CourseClass> courseList = new ArrayList<>();
        courseList.add(new CourseClass(getString(R.string.str_android), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_ios), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_database), getString(R.string.str_grade_incomplete)));
        courseList.add(new CourseClass(getString(R.string.str_oop), getString(R.string.str_grade_incomplete)));

        // Create an instance of CardAdapter and pass the list of CourseClass objects to it
        CardAdapter adapter = new CardAdapter(courseList);

        // Set the adapter on RecyclerView
        recyclerView.setAdapter(adapter);

        // Set a layout manager on RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}