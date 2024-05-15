package com.example.globalgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetGradeActivity extends AppCompatActivity {
    EditText inputGradeEditText;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_grade);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        inputGradeEditText = findViewById(R.id.input_grade);
        confirmButton = findViewById(R.id.btn_confirm);

        // Extract data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String courseTitle = intent.getStringExtra("course_name");
            String courseModule = intent.getStringExtra("course_module");

            // Set the course title and module in the TextViews
            TextView titleTextView = findViewById(R.id.course_title);
            titleTextView.setText(courseTitle);

            TextView moduleTextView = findViewById(R.id.course_module);
            moduleTextView.setText(courseModule);

            TextView moduleTitleTextView = findViewById(R.id.module_title);

            // Set module title based on selected module
            String moduleTitle = getModuleTitle(courseModule);
            moduleTitleTextView.setText(moduleTitle);
        }

        // Handle input grade and send it back to MainActivity
        confirmButton.setOnClickListener(v -> {
            // Get the input grade
            String gradeString = inputGradeEditText.getText().toString().trim();
            String courseTitle = intent.getStringExtra("course_name");

            Double grade = Double.parseDouble(gradeString);

            // Create an intent to send back the grade to MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("grade", grade);
            resultIntent.putExtra("course_name", courseTitle);

            System.out.println("Nota registrada " + grade);

            // Set the result and finish the activity
            setResult(RESULT_OK, resultIntent);

            finish();
        });
    }

    private String getModuleTitle(String courseModule) {
        switch (courseModule) {
            case "Module 1":
            case "Módulo 1":
            case "第一模块":
                return getString(R.string.str_module_one);

            case "Module 2":
            case "Módulo 2":
            case "第二模块":
                return getString(R.string.str_module_two);

            case "Module 3":
            case "Módulo 3":
            case "第三模块":
                return getString(R.string.str_module_three);

            case "Module 4":
            case "Módulo 4":
            case "第四模块":
                return getString(R.string.str_module_four);

            default:
                return "Algo deu errado!";
        }
    }
}
