package gurug.student.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

import gurug.student.R;
import gurug.student.adapter.SubjectsAdapter;
import gurug.student.model.Subject;

/**
 * Created by moin on 29/9/16.
 */
public class SubjectActivity extends AppCompatActivity{
    private RecyclerView mSubjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSubjects = (RecyclerView) findViewById(R.id.subjects);
        mSubjects.setLayoutManager(new GridLayoutManager(SubjectActivity.this,2));
        mSubjects.setAdapter(new SubjectsAdapter(SubjectActivity.this, new ArrayList<Subject>(SQLite.select().from(Subject.class).queryList())));
       }

}
