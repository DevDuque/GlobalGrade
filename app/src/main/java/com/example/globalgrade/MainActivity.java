package com.example.globalgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private CardAdapter adapter;
    private List<CourseClass> courseList = new ArrayList<>();

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
    }

    public View.OnClickListener onCardClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                CourseClass course = courseList.get(position);
                String selectedModule = spinner.getSelectedItem().toString();

                Intent nextPage = new Intent(MainActivity.this, SetGradeActivity.class);
                nextPage.putExtra("course_name", course.getTitle());
                nextPage.putExtra("course_module", selectedModule);
                startActivityForResult(nextPage, 1);
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
                double updatedGrade = data.getDoubleExtra("grade", 0.0); // Default value is 0.0
                String courseTitle = data.getStringExtra("course_name");
                // Update the corresponding CourseClass object with the updated grade
                updateCourseGrade(courseTitle, updatedGrade);
            }
        }
    }

    // Method to update the grade of the corresponding CourseClass object
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
        courseList.add(new CourseClass(getString(R.string.str_computers), getExistingGrade(getString(R.string.str_computers))));
        courseList.add(new CourseClass(getString(R.string.str_mobile), null));
        courseList.add(new CourseClass(getString(R.string.str_programming), null));
        courseList.add(new CourseClass(getString(R.string.str_programmingLab), null));
        adapter.notifyDataSetChanged();
    }

    private void populateRecyclerViewForChoice2() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_oop), null));
        courseList.add(new CourseClass(getString(R.string.str_database), null));
        courseList.add(new CourseClass(getString(R.string.str_ads), null));
        courseList.add(new CourseClass(getString(R.string.str_devLab), null));
        adapter.notifyDataSetChanged();
    }

    private void populateRecyclerViewForChoice3() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_android), null));
        courseList.add(new CourseClass(getString(R.string.str_ios), null));
        courseList.add(new CourseClass(getString(R.string.str_specialOop), null));
        courseList.add(new CourseClass(getString(R.string.str_specialDatabase), null));
        adapter.notifyDataSetChanged();
    }

    private void populateRecyclerViewForChoice4() {
        courseList.clear();
        courseList.add(new CourseClass(getString(R.string.str_specialAndroid), null));
        courseList.add(new CourseClass(getString(R.string.str_specialIOS), null));
        courseList.add(new CourseClass(getString(R.string.str_multiplatform), null));
        courseList.add(new CourseClass(getString(R.string.str_profissional), null));
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
