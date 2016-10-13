package gurug.student.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import gurug.student.R;
import gurug.student.activity.CustomizeDrawable;
import gurug.student.activity.MathsActivity;
import gurug.student.model.Subject;
import gurug.student.model.Subject_Table;

import static android.R.attr.progressDrawable;
import static android.R.attr.settingsActivity;
import static android.R.attr.textColor;
import static gurug.student.R.id.point;
import static gurug.student.R.id.subject;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> implements OnItemClickListenerSubjects {
    private Context mContext;
    private static ArrayList<Subject> mMySunjectsArrayList;
    private ProgressBar mProgress;

    public SubjectsAdapter(Context context, ArrayList<Subject> lMySubjectsArrayList) {
        mContext = context;
        mMySunjectsArrayList = lMySubjectsArrayList;
    }

    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_item, parent, false);
        ViewHolder lViewHolder = new ViewHolder(lView);
        return lViewHolder;
    }

    @Override
    public void onBindViewHolder(SubjectsAdapter.ViewHolder holder, int position) {
        Subject subject = mMySunjectsArrayList.get(position);
        holder.bind(subject, this);
        holder.mPoints.setText(subject.getPoints());
        holder.mLevel.setText(subject.getLevel());
        holder.mSubjectName.setText(subject.getSubjectName());

        if(subject.getSubjectName().equals("Maths")){
            Log.i("TAG","TAG: "+subject.getColor());
            holder.mLevel.setTextColor(Color.parseColor(subject.getColor()));
            holder.mPoints.setTextColor(Color.parseColor(subject.getColor()));
            //holder.mProgressBar.setBackgroundColor((Color.parseColor(subject.getColor())));
            /*holder.mProgressBar.setBackgroundResource(R.drawable.blue_progress);
            GradientDrawable drawable = (GradientDrawable) holder.mProgressBar.getBackground();
            drawable.setColor(Color.parseColor("#82ca9c"));*/
        }
        else if(subject.getSubjectName().equals("Science")){
            Log.i("TAG","TAG: "+subject.getColor());
            holder.mLevel.setTextColor(Color.parseColor(subject.getColor()));
            holder.mPoints.setTextColor(Color.parseColor(subject.getColor()));
        }
        else if(subject.getSubjectName().equals("S.Science")){
            Log.i("TAG","TAG: "+subject.getColor());
            holder.mLevel.setTextColor(Color.parseColor(subject.getColor()));
            holder.mPoints.setTextColor(Color.parseColor(subject.getColor()));
        }
        else if(subject.getSubjectName().equals("English")){
            Log.i("TAG","TAG: "+subject.getColor());
            holder.mLevel.setTextColor(Color.parseColor(subject.getColor()));
            holder.mPoints.setTextColor(Color.parseColor(subject.getColor()));
        }
        else if(subject.getSubjectName().equals("Kannada")){
            Log.i("TAG","TAG: "+subject.getColor());
            holder.mLevel.setTextColor(Color.parseColor(subject.getColor()));
            holder.mPoints.setTextColor(Color.parseColor(subject.getColor()));
        }
    }

    @Override
    public int getItemCount() {
        return mMySunjectsArrayList.size();
    }

    @Override
    public void onItemClick(Subject subject) {
        Intent lIntent = new Intent(mContext, MathsActivity.class);
        lIntent.putExtra("subject", subject.getSubjectName());
        lIntent.putExtra("color", subject.getColor());
        lIntent.putExtra("level", subject.getLevel());
        lIntent.putExtra("points", subject.getPoints());
        mContext.startActivity(lIntent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mSubjectName, mLevel, mPoints;
        ProgressBar mProgressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            mSubjectName = (TextView) itemView.findViewById(subject);
            mLevel = (TextView) itemView.findViewById(R.id.level);
            mPoints = (TextView) itemView.findViewById(point);
            mProgressBar    =   (ProgressBar) itemView.findViewById(R.id.progress_bar);

            ObjectAnimator progressAnimator1 = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 70);
            progressAnimator1.setDuration(3000);
            progressAnimator1.setInterpolator(new LinearInterpolator());
            progressAnimator1.start();
        }

        public void bind(final Subject item, final OnItemClickListenerSubjects listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
