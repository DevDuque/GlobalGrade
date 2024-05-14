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

    public View.OnClickListener onCardClicked ()  {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make instance to next page
                Intent nextPage = new Intent(getBaseContext(), SetGradeActivity.class);

                // Start the activity of the next page
                startActivity(nextPage);
            }
        };
    }

    public void InitSpinner() {
        // Find the Spinner by its ID
        spinner = findViewById(R.id.course_spinner);

        // Set up Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.course_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Set listener for Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Handle spinner item selection here
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

    public void InitRecycleView() {
        // Find the RecyclerView by its ID
        recyclerView = findViewById(R.id.recycler_view);

        // Create an instance of CardAdapter
        adapter = new CardAdapter(MainActivity.this, courseList);

        // Set the adapter on RecyclerView
        recyclerView.setAdapter(adapter);

        // Set a layout manager on RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method to populate RecyclerView with data for Spinner choice 1
    private void populateRecyclerViewForChoice1() {
        courseList.clear(); // Clear previous data
        courseList.add(new CourseClass(getString(R.string.str_computers), 50.0));
        courseList.add(new CourseClass(getString(R.string.str_mobile), 75.0));
        courseList.add(new CourseClass(getString(R.string.str_programming), 90.0));
        courseList.add(new CourseClass(getString(R.string.str_programmingLab), 85.0));

        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();
    }

    // Method to populate RecyclerView with data for Spinner choice 2
    private void populateRecyclerViewForChoice2() {
        courseList.clear(); // Clear previous data
        courseList.add(new CourseClass(getString(R.string.str_oop), null));
        courseList.add(new CourseClass(getString(R.string.str_database), null));
        courseList.add(new CourseClass(getString(R.string.str_ads), null));
        courseList.add(new CourseClass(getString(R.string.str_devLab), null));

        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();
    }

    // Method to populate RecyclerView with data for Spinner choice 3
    private void populateRecyclerViewForChoice3() {
        courseList.clear(); // Clear previous data
        courseList.add(new CourseClass(getString(R.string.str_android), null));
        courseList.add(new CourseClass(getString(R.string.str_ios), null));
        courseList.add(new CourseClass(getString(R.string.str_specialOop), null));
        courseList.add(new CourseClass(getString(R.string.str_specialDatabase), null));

        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();
    }

    // Method to populate RecyclerView with data for Spinner choice 4
    private void populateRecyclerViewForChoice4() {
        courseList.clear(); // Clear previous data
        courseList.add(new CourseClass(getString(R.string.str_specialAndroid), null));
        courseList.add(new CourseClass(getString(R.string.str_specialIOS), null));
        courseList.add(new CourseClass(getString(R.string.str_multiplatform), null));
        courseList.add(new CourseClass(getString(R.string.str_profissional), null));

        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();
    }
}
