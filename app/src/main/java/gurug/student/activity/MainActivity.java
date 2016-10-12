package gurug.student.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import gurug.student.R;
import gurug.student.model.Subject;
import gurug.student.model.Subject_Table;

import static android.R.id.progress;
import static com.raizlabs.android.dbflow.sql.language.property.PropertyFactory.from;
import static gurug.student.model.Subject_Table.points;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mContent, mPlay, mProfile, mSettings;
    private TextView mMathsScore, mScienceScore, mSocialScienceScore, mEnglishScore, mKannadaScore;
    private int sum1 = 1;
    private List<Subject> mTableList1, mTableList2, mTableList3, mTableList4, mTableList5;
    private String value;
    private static String mMaths= "demo", mScience= "demo", mSocialScience= "demo", mEnglish= "demo", mKannada = "demo";
    private static String mMathsLevel= "demo", mScienceLevel= "demo", mSocialScienceLevel= "demo", mEnglishLevel= "demo", mKannadaLevel = "demo";
    private TextView mMathsSubject;
    private TextView mScienceSubject;
    private TextView mSocialScienceSubject;
    private TextView mEnglishSubject;
    private TextView mKannadaSubject;
    private String progressString = "";

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

        mContent              =   (RelativeLayout) findViewById(R.id.mycontent);
        mPlay                 =   (RelativeLayout) findViewById(R.id.play);
        mProfile              =   (RelativeLayout) findViewById(R.id.myprofile);
        mSettings             =   (RelativeLayout) findViewById(R.id.mysettings);
        mMathsScore           =   (TextView) findViewById(R.id.maths1_score);
        mScienceScore         =   (TextView) findViewById(R.id.science_score);
        mSocialScienceScore   =   (TextView) findViewById(R.id.social_science_score);
        mEnglishScore         =   (TextView) findViewById(R.id.english_score);
        mKannadaScore         =   (TextView) findViewById(R.id.kannada_score);
        mMathsSubject         =    (TextView) findViewById(R.id.maths1);
        mScienceSubject       =    (TextView) findViewById(R.id.science1);
        mSocialScienceSubject =   (TextView) findViewById(R.id.social_science1);
        mEnglishSubject        =   (TextView) findViewById(R.id.english1);
        mKannadaSubject       =   (TextView) findViewById(R.id.kannada1);

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
            subject.setColor("#82ca9c");
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
            subject.setColor("#ffb74d");
            subject.save();
            //English
            subject= new Subject();
            subject.setSubjectName("English");
            subject.setLevel("3");
            subject.setPoints("40");
            subject.setColor("#ce93d8");
            subject.save();
            //Kannada
            subject= new Subject();
            subject.setSubjectName("Kannada");
            subject.setLevel("4");
            subject.setPoints("50");
            subject.setColor("#c0ca33");
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

    private void showData(ArrayList<Subject> mTableArrayList) {
       Log.i("TAG","TAG: "+mTableArrayList.toString());
        for(int i=0; i<mTableArrayList.size(); i++){
            Log.i("TAG","TAG: "+ mTableArrayList.get(i).getSubjectName());
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        getData();
        mMathsScore.setText(mMaths);
        mScienceScore.setText(mScience);
        mSocialScienceScore.setText(mSocialScience);
        mEnglishScore.setText(mEnglish);
        mKannadaScore.setText(mKannada);

        mMathsSubject.setText("Maths | "+mMathsLevel);
        mScienceSubject.setText("Science | "+mScienceLevel);
        mSocialScienceSubject.setText("Social Science | "+mSocialScienceLevel);
        mEnglishSubject.setText("English | "+mEnglishLevel);
        mKannadaSubject.setText("Kannada | "+mKannadaLevel);
        startAnimation();
    }

    private void getData(){

        mTableList1 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("Maths")).
                queryList();
        Log.i("TAG","TAG"+mTableList1);
        for(int i=0; i<mTableList1.size(); i++){
            Log.i("TAG","TAG: "+ mTableList1.get(i).getPoints());
            Log.i("TAG","TAG: "+ mTableList1.get(i).getColor());
            mMaths = mTableList1.get(i).getPoints();
            mMathsLevel   =   mTableList1.get(i).getLevel();
        }

        mTableList2 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("Science")).
                queryList();
        Log.i("TAG","TAG"+mTableList2);
        for(int i=0; i<mTableList2.size(); i++){
            Log.i("TAG","TAG: "+ mTableList2.get(i).getPoints());
            mScience = mTableList2.get(i).getPoints();
            mScienceLevel   =   mTableList2.get(i).getLevel();
        }

        mTableList3 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("S.Science")).
                queryList();
        Log.i("TAG","TAG"+mTableList3);
        for(int i=0; i<mTableList3.size(); i++){
            Log.i("TAG","TAG: "+ mTableList3.get(i).getPoints());
            mSocialScience = mTableList3.get(i).getPoints();
            mSocialScienceLevel =   mTableList3.get(i).getLevel();
        }

        mTableList4 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("English")).
                queryList();
        Log.i("TAG","TAG"+mTableList4);
        for(int i=0; i<mTableList4.size(); i++){
            Log.i("TAG","TAG: "+ mTableList4.get(i).getPoints());
            mEnglish = mTableList4.get(i).getPoints();
            mEnglishLevel   =   mTableList4.get(i).getLevel();
        }

        mTableList5 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("Kannada")).
                queryList();
        Log.i("TAG","TAG"+mTableList5);
        for(int i=0; i<mTableList5.size(); i++){
            Log.i("TAG","TAG: "+ mTableList5.get(i).getPoints());
            mKannada = mTableList5.get(i).getPoints();
            mKannadaLevel   =   mTableList5.get(i).getLevel();
        }


    }

    private void startAnimation(){
            final ProgressBar mProgressBar1 = (ProgressBar) findViewById(R.id.progress_bar1);
            ObjectAnimator progressAnimator1 = ObjectAnimator.ofInt(mProgressBar1, "progress", 0, 50);
            progressAnimator1.setDuration(3000);
            progressAnimator1.setInterpolator(new LinearInterpolator());
            progressAnimator1.start();

            final ProgressBar mProgressBar2 = (ProgressBar) findViewById(R.id.progress_bar2);
            ObjectAnimator progressAnimator2 = ObjectAnimator.ofInt(mProgressBar2, "progress", 0, 80);
            progressAnimator2.setDuration(3000);
            progressAnimator2.setInterpolator(new LinearInterpolator());
            progressAnimator2.start();

            final ProgressBar mProgressBar3 = (ProgressBar) findViewById(R.id.progress_bar3);
            ObjectAnimator progressAnimator3 = ObjectAnimator.ofInt(mProgressBar3, "progress", 0, 60);
            progressAnimator3.setDuration(3000);
            progressAnimator3.setInterpolator(new LinearInterpolator());
            progressAnimator3.start();

            final ProgressBar mProgressBar4 = (ProgressBar) findViewById(R.id.progress_bar4);
            ObjectAnimator progressAnimator4 = ObjectAnimator.ofInt(mProgressBar4, "progress", 0, 70);
            progressAnimator4.setDuration(3000);
            progressAnimator4.setInterpolator(new LinearInterpolator());
            progressAnimator4.start();

            final ProgressBar mProgressBar5 = (ProgressBar) findViewById(R.id.progress_bar5);
            ObjectAnimator progressAnimator5 = ObjectAnimator.ofInt(mProgressBar5, "progress", 0, 90);
            progressAnimator5.setDuration(3000);
            progressAnimator5.setInterpolator(new LinearInterpolator());
            progressAnimator5.start();

    }
}
