package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gurug.student.R;
import gurug.student.model.Subject;
import gurug.student.model.Subject_Table;

public class GameStartsActivity extends AppCompatActivity {

    private TextView mSecond;
    private TextView mLevel1, mLevel2;
    private int sum = 0;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    int i = 5;
    private List<Subject> mTableList1, mTableList2, mTableList3, mTableList4, mTableList5;
    private String mLevel1Data = "demo", mLevel2Data = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starts);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        mSecond      =   (TextView) findViewById(R.id.seconds);
        mLevel1      =   (TextView) findViewById(R.id.level1);
        mLevel2      =   (TextView) findViewById(R.id.level2);

        //sum = sum + Integer.parseInt((getIntent().getStringExtra("level")));
        //mLevel1.setText(getIntent().getStringExtra("level"));
        //mLevel2.setText(sum+"");
    }


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        if (timer != null) {
            initializeTimerTask();
        }
        //schedule the timer, after the first 50ms the TimerTask will run every 1000ms
        timer.schedule(timerTask, 50, 1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mSecond.setText(""+i);
                        i--;
                        if(i==0){
                            stoptimertask();
                            startActivity(new Intent(GameStartsActivity.this, QuestionsActivity.class));
                            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
                            finish();
                        }

                    }
                });
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        getData();
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
            mLevel1Data   =   mTableList1.get(i).getLevel();
        }

        mTableList2 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("Science")).
                queryList();
        Log.i("TAG","TAG"+mTableList2);
        for(int i=0; i<mTableList2.size(); i++){
            Log.i("TAG","TAG: "+ mTableList2.get(i).getPoints());
            mLevel1Data   =   mTableList2.get(i).getLevel();
        }

        mTableList3 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("S.Science")).
                queryList();
        Log.i("TAG","TAG"+mTableList3);
        for(int i=0; i<mTableList3.size(); i++){
            Log.i("TAG","TAG: "+ mTableList3.get(i).getPoints());
            mLevel1Data =   mTableList3.get(i).getLevel();
        }

        mTableList4 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("English")).
                queryList();
        Log.i("TAG","TAG"+mTableList4);
        for(int i=0; i<mTableList4.size(); i++){
            Log.i("TAG","TAG: "+ mTableList4.get(i).getPoints());
            mLevel1Data   =   mTableList4.get(i).getLevel();
        }

        mTableList5 = SQLite.select().
                from(Subject.class).
                where(Subject_Table.subjectName.eq("Kannada")).
                queryList();
        Log.i("TAG","TAG"+mTableList5);
        for(int i=0; i<mTableList5.size(); i++){
            Log.i("TAG","TAG: "+ mTableList5.get(i).getPoints());
            mLevel1Data   =   mTableList5.get(i).getLevel();
        }

        mLevel1.setText(mLevel1Data);
        sum = Integer.parseInt(mLevel1Data) + 1;
        mLevel2.setText(""+sum);
    }
}
