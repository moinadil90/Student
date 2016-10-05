package gurug.student.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gurug.student.R;
import gurug.student.activity.MathsActivity;
import gurug.student.model.Subject;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> implements OnItemClickListenerSubjects {
    private Context mContext;
    private ArrayList<Subject> mMySunjectsArrayList;

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
        mContext.startActivity(lIntent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mSubjectName, mLevel, mPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            mSubjectName = (TextView) itemView.findViewById(R.id.subject);
            mLevel = (TextView) itemView.findViewById(R.id.level);
            mPoints = (TextView) itemView.findViewById(R.id.point);
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
