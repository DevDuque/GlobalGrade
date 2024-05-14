package com.example.globalgrade.classes;

import android.content.Context;
import android.content.res.Configuration;
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
    private Context context;
    private static View.OnClickListener cardClickListener;

    public CardAdapter(Context context, List<CourseClass> courseClassesList, View.OnClickListener cardClickListener) {
        this.context = context;
        this.courseClassesList = courseClassesList;
        this.cardClickListener = cardClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitleTextView;
        public TextView courseGradeTextView;
        public ImageView courseImageView;

        public MyViewHolder(View v) {
            super(v);
            courseTitleTextView = v.findViewById(R.id.course_title);
            courseGradeTextView = v.findViewById(R.id.course_grade);
            courseImageView = v.findViewById(R.id.course_image);


            v.setOnClickListener(cardClickListener);
        }
    }

    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_clean, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CourseClass courseClass = courseClassesList.get(position);
        holder.courseTitleTextView.setText(courseClass.getTitle());

        int imageResource;

        if (courseClass.getGrade() != null && courseClass.getGrade() != null) {
            String completeGrade = context.getString(R.string.str_grade_complete) + courseClass.getGrade();
            holder.courseGradeTextView.setText(completeGrade);
            imageResource = courseClass.getApproved() ?
                    (isLightTheme() ? R.drawable.light_icon_check : R.drawable.dark_icon_check) :
                    (isLightTheme() ? R.drawable.light_icon_fail : R.drawable.dark_icon_fail);
        } else {
            holder.courseGradeTextView.setText(context.getString(R.string.str_grade_incomplete));
            imageResource = isLightTheme() ? R.drawable.light_icon_help : R.drawable.dark_icon_help;
        }

        holder.courseImageView.setImageResource(imageResource);

        // Set the tag to hold the position of the item
        holder.itemView.setTag(position);
    }

    private boolean isLightTheme() {
        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_NO;
    }

    @Override
    public int getItemCount() {
        return courseClassesList.size();
    }
}
