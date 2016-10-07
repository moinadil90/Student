package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import gurug.student.R;

public class GameStartsActivity extends AppCompatActivity {

    private TextView mSecond;
    private TextView mLevel1, mLevel2;
    private int sum = 1;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    int i = 5;

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
    }
}
