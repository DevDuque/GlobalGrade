package com.example.globalgrade.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgrade.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<CourseClass> courseClassesList;

    public CardAdapter(List<CourseClass> courseClassesList) {
        this.courseClassesList = courseClassesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_clean, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CourseClass courseClass = courseClassesList.get(position);
        holder.courseTitleTextView.setText(courseClass.getTitle());
        holder.courseGradeTextView.setText(courseClass.getGrade());
    }

    @Override
    public int getItemCount() {
        return courseClassesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitleTextView;
        public TextView courseGradeTextView;
        public ImageView courseImageView;

        public MyViewHolder(View view) {
            super(view);
            courseTitleTextView = view.findViewById(R.id.course_title);
            courseGradeTextView = view.findViewById(R.id.course_grade);
            courseImageView = view.findViewById(R.id.course_image);
        }
    }
}
