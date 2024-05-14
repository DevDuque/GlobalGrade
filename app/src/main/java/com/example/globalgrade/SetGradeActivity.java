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
    EditText inputGradeEditText = findViewById(R.id.input_grade);
    Button confirmButton = findViewById(R.id.btn_confirm);

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
        }

        // Handle input grade and send it back to MainActivity

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input grade
                String grade = inputGradeEditText.getText().toString().trim();

                // Create an intent to send back the grade to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("grade", grade);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
