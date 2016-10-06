package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import gurug.student.R;
import gurug.student.model.Subject;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mContent, mPlay, mProfile, mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContent = (RelativeLayout) findViewById(R.id.mycontent);
        mPlay = (RelativeLayout) findViewById(R.id.play);
        mProfile = (RelativeLayout) findViewById(R.id.myprofile);
        mSettings = (RelativeLayout) findViewById(R.id.mysettings);

        mContent.setOnClickListener(new Listener());
        mPlay.setOnClickListener(new Listener());
        mProfile.setOnClickListener(new Listener());
        mSettings.setOnClickListener(new Listener());


        //if(!(SQLite.select().from(Subject.class).where(Subject_Table.subjectName.eq("MATHS")).queryList().size()>0)){
        if(!(SQLite.select().from(Subject.class).queryList().size()>0)){
            initSubjetsDataBase();
        }
    }

    private void initSubjetsDataBase() {

            //Maths
            Subject subject= new Subject();
            subject.setSubjectName("Maths");
            subject.setLevel("0");
            subject.setPoints("10");
            subject.setColor("#6dcff6");
            subject.save();
            //SCIENCE
            subject= new Subject();
            subject.setSubjectName("Science");
            subject.setLevel("1");
            subject.setPoints("20");
            subject.setColor("#6dcff6");
            subject.save();
            //SOCIAL SCIENCE
            subject= new Subject();
            subject.setSubjectName("S.Science");
            subject.setLevel("2");
            subject.setPoints("30");
            subject.setColor("#6dcff6");
            subject.save();
            //English
            subject= new Subject();
            subject.setSubjectName("English");
            subject.setLevel("3");
            subject.setPoints("40");
            subject.setColor("#6dcff6");
            subject.save();
            //Kannada
            subject= new Subject();
            subject.setSubjectName("Kannada");
            subject.setLevel("4");
            subject.setPoints("50");
            subject.setColor("#6dcff6");
            subject.save();
    }
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mycontent:
                    Toast.makeText(MainActivity.this, "My Content has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.play:
                    //Toast.makeText(MainActivity.this, "Play has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SubjectActivity.class));
                    break;
                case R.id.myprofile:
                    Toast.makeText(MainActivity.this, "My Profile has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mysettings:
                    Toast.makeText(MainActivity.this, "My Settings has been clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
