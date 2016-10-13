package gurug.student.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
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
    private int sum1 = 0;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    int i = 5;
    private List<Subject> mTableList1, mTableList2, mTableList3, mTableList4, mTableList5;
    private String mLevel1Data = "demo", mLevel2Data = "demo";
    private int check = 0;
    private ProgressBar mProgressBar;

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
        mProgressBar =   (ProgressBar) findViewById(R.id.progress_bar);

        sum1 = MathsActivity.sum - 1;
        mLevel1.setText(sum1+"");
        mLevel2.setText(MathsActivity.sum+"");
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
                        startMusic();
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
        startAnimation();
    }
    private void startMusic(){
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.start();

    }
    private void startAnimation() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ObjectAnimator progressAnimator1 = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 70);
        progressAnimator1.setDuration(3000);
        progressAnimator1.setInterpolator(new LinearInterpolator());
        progressAnimator1.start();
    }
}
