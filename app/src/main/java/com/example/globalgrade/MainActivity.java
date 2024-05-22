package com.example.globalgrade;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgrade.classes.CardAdapter;
import com.example.globalgrade.classes.CourseClass;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private CardAdapter adapter;
    private final List<CourseClass> courseList = new ArrayList<>();

    private DrawerLayout drawerLayout;
    private ImageButton menuToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        menuToggle = findViewById(R.id.menu_toggle);

        // Set click listener for menu toggle button
        menuToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        InitSpinner();
        InitRecycleView();
    }

    public void exitApp(View view) {
        // Finish the current activity to exit the application
        finish();
    }

    // Override onOptionsItemSelected to handle ActionBarDrawerToggle events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the ActionBarDrawerToggle
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public View.OnClickListener onCardClicked() {
        return v -> {
            int position = recyclerView.getChildAdapterPosition(v);
            CourseClass course = courseList.get(position);
            String selectedModule = spinner.getSelectedItem().toString();

            Double existingGrade = getExistingGrade(course.getTitle());

            if (existingGrade == null) {
                // Grade is null, navigate to SetGradeActivity
                Intent nextPage = new Intent(MainActivity.this, SetGradeActivity.class);
                nextPage.putExtra("course_name", course.getTitle());
                nextPage.putExtra("course_module", selectedModule);
                startActivityForResult(nextPage, 1);
            } else {
                // Grade exists, show toast message
                String message = getString(R.string.phrase_Start) + " " + course.getTitle() + " " + getString(R.string.phrase_End);

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void InitSpinner() {
        spinner = findViewById(R.id.course_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.course_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        populateRecyclerViewForChoice1();
                        break;
                    case 1:
                        populateRecyclerViewForChoice2();
                        break;
                    case 3:
                        populateRecyclerViewForChoice4();
                        break;
                    default:
                        populateRecyclerViewForChoice3();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    // Override onActivityResult() to receive updated grade
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                // Retrieve the grade from the intent
                double updatedGrade = data.getDoubleExtra("grade", 0.0);
                String courseTitle = data.getStringExtra("course_name");

                // Check if grade is correctly received
                System.out.println("Received grade: " + updatedGrade);
                System.out.println("Received course name: " + courseTitle);

                // Update the corresponding CourseClass object with the updated grade
                updateCourseGrade(courseTitle, updatedGrade);
            }
        }
    }


    // Method to update the grade of the corresponding CourseClass object
    @SuppressLint("NotifyDataSetChanged")
    private void updateCourseGrade(String courseTitle, double grade) {
        for (CourseClass course : courseList) {
            if (course.getTitle().equals(courseTitle)) {
                course.setGrade(grade); // Set the updated grade
                break;
            }
        }
        adapter.notifyDataSetChanged(); // Notify adapter of data change
    }

    public void InitRecycleView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new CardAdapter(MainActivity.this, courseList, onCardClicked()); // Pass the click listener
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateRecyclerViewForChoice1() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_computers)));
        courseList.add(new CourseClass(getString(R.string.str_mobile)));
        courseList.add(new CourseClass(getString(R.string.str_programming)));
        courseList.add(new CourseClass(getString(R.string.str_programmingLab)));
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void populateRecyclerViewForChoice2() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_oop)));
        courseList.add(new CourseClass(getString(R.string.str_database)));
        courseList.add(new CourseClass(getString(R.string.str_ads)));
        courseList.add(new CourseClass(getString(R.string.str_devLab)));
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void populateRecyclerViewForChoice3() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_android)));
        courseList.add(new CourseClass(getString(R.string.str_ios)));
        courseList.add(new CourseClass(getString(R.string.str_specialOop)));
        courseList.add(new CourseClass(getString(R.string.str_specialDatabase)));
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void populateRecyclerViewForChoice4() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_specialAndroid)));
        courseList.add(new CourseClass(getString(R.string.str_specialIOS)));
        courseList.add(new CourseClass(getString(R.string.str_multiplatform)));
        courseList.add(new CourseClass(getString(R.string.str_profissional)));
        adapter.notifyDataSetChanged();
    }

    private Double getExistingGrade(String courseTitle) {
        // Iterate through the existing courseList to find the matching title
        for (CourseClass course : courseList) {
            if (course.getTitle().equals(courseTitle)) {
                // If found, return the existing grade
                return course.getGrade();
            }
        }
        // If not found, return null
        return null;
    }
}
