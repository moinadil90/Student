package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import static com.raizlabs.android.dbflow.sql.language.property.PropertyFactory.from;
import static gurug.student.model.Subject_Table.points;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mContent, mPlay, mProfile, mSettings;
    private TextView mMathsScore, mScienceScore, mSocialScienceScore, mEnglishScore, mKannadaScore;
    private int sum1 = 1;
    private List<Subject> mTableList;
    private String value;

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
                    mTableList = SQLite.select().
                                 from(Subject.class).
                                 where(Subject_Table.subjectName.eq("Maths")).
                                 queryList();
                    Log.i("TAG","TAG"+mTableList);

                    for(int i=0; i<mTableList.size(); i++){
                        Log.i("TAG","TAG: "+ mTableList.get(i).getPoints());
                    }



                    if ((mTableList != null) && (mTableList.size() > 0)) {
                        //showData(new ArrayList<Subject>(mTableList));
                    }

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

}
