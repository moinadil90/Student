package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import gurug.student.R;

/**
 * Created by moin on 29/9/16.
 */
public class SubjectActivity extends AppCompatActivity{
    private RelativeLayout mSubject1, mSubject2, mSubject3, mSubject4, mSubject5;

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

        mSubject1 = (RelativeLayout) findViewById(R.id.subject1);
        mSubject2 = (RelativeLayout) findViewById(R.id.subject2);
        mSubject3 = (RelativeLayout) findViewById(R.id.subject3);
        mSubject4 = (RelativeLayout) findViewById(R.id.subject4);
        mSubject5 = (RelativeLayout) findViewById(R.id.subject5);

        mSubject1.setOnClickListener(new Listener());
        mSubject2.setOnClickListener(new Listener());
        mSubject3.setOnClickListener(new Listener());
        mSubject4.setOnClickListener(new Listener());
        mSubject5.setOnClickListener(new Listener());
    }
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.subject1:
                    //Toast.makeText(SubjectActivity.this, "Subject1 has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SubjectActivity.this, MathsActivity.class));
                    break;
                case R.id.subject2:
                    Toast.makeText(SubjectActivity.this, "Subject2 has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.subject3:
                    Toast.makeText(SubjectActivity.this, "Subject3 has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.subject4:
                    Toast.makeText(SubjectActivity.this, "Subject4 has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.subject5:
                    Toast.makeText(SubjectActivity.this, "Subject5 has been clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
