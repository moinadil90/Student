package gurug.student.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import gurug.student.R;
import gurug.student.api.HttpCalls;
import gurug.student.callback.OnTaskCompleted;
import gurug.student.model.Question;
import gurug.student.util.RestConstants;
import gurug.student.util.Utility;

/**
 * Created by moin on 29/9/16.
 */
public class QuestionsActivity extends AppCompatActivity implements OnTaskCompleted {
    private TextView mOption1, mOption2, mOption3, mOption4, mQuestion;
    private static TextView mAnswer;
    private ImageButton mPlay, mPause, mClose;
    private ArrayList<String> mQuestionArrayList = new ArrayList<>();
    private ArrayList<String> mAnswerArrayList = new ArrayList<>();
    private static int randomNum;
    private ProgressBar mProgressBar;
    private static int progress = 0;

    private TextView mSecond;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        mOption1        =   (TextView) findViewById(R.id.option1);
        mOption2        =   (TextView) findViewById(R.id.option2);
        mOption3        =   (TextView) findViewById(R.id.option3);
        mOption4        =   (TextView) findViewById(R.id.option4);
        mPlay           =   (ImageButton) findViewById(R.id.play);
        mPause          =   (ImageButton) findViewById(R.id.pause);
        mClose          =   (ImageButton) findViewById(R.id.close);
        mQuestion       =   (TextView) findViewById(R.id.question);
        mAnswer         =   (TextView) findViewById(R.id.option3);
        mProgressBar    =   (ProgressBar) findViewById(R.id.progress_bar);

        mOption1.setOnClickListener(new Listener());
        mOption2.setOnClickListener(new Listener());
        mOption3.setOnClickListener(new Listener());
        mOption4.setOnClickListener(new Listener());

        mPlay.setOnClickListener(new Listener());
        mPause.setOnClickListener(new Listener());
        mClose.setOnClickListener(new Listener());
    }

    private void Progress() {
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //this will be done every 1000 milliseconds ( 1 seconds )
                //int progress = (int) (60000+(millisUntilFinished) / 1000);
                progress++;
                mProgressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                //the progressBar will be invisible after 60 000 miliseconds ( 1 minute)
                //Toast.makeText(QuestionsActivity.this, "Hi", Toast.LENGTH_SHORT).show();
                progress = 0;
                //mProgressBar.setBackgroundColor(Color.parseColor("#6dcff6"));
            }

        }.start();
    }

    @Override
    public void onTaskCompleted(String response, String lPurpose) {
        if (lPurpose.equalsIgnoreCase("QUESTION_API")) {
                JSONArray lJSJsonArray = null;
            try {
                lJSJsonArray = new JSONArray(response);
                for (int j = 0; j < lJSJsonArray.length(); j++) {
                    Log.i("onTaskCompleted", "onTaskCompleted");
                    Question lMyQuestion = new Question();
                    lMyQuestion.setId(lJSJsonArray.getJSONObject(j).getString("id"));
                    lMyQuestion.setAnswer(lJSJsonArray.getJSONObject(j).getString("answer"));
                    lMyQuestion.setQuestion(lJSJsonArray.getJSONObject(j).getString("question"));
                    lMyQuestion.setValue(lJSJsonArray.getJSONObject(j).getString("value"));
                    lMyQuestion.setAirdate(lJSJsonArray.getJSONObject(j).getString("airdate"));
                    lMyQuestion.setCreated_at(lJSJsonArray.getJSONObject(j).getString("created_at"));
                    lMyQuestion.setUpdated_at(lJSJsonArray.getJSONObject(j).getString("updated_at"));
                    lMyQuestion.setCategory_id(lJSJsonArray.getJSONObject(j).getString("category_id"));
                    lMyQuestion.setGame_id((lJSJsonArray.getJSONObject(j).getString("game_id")));
                    lMyQuestion.setInvalid_count(lJSJsonArray.getJSONObject(j).getString("invalid_count"));
                    lMyQuestion.setCategory(lJSJsonArray.getJSONObject(j).getString("category"));
                    mQuestionArrayList.add(lJSJsonArray.getJSONObject(j).getString("question"));
                    mAnswerArrayList.add(lJSJsonArray.getJSONObject(j).getString("answer"));
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //startTimer();
    }

    @Override
    public void onTaskError(String response, String lPurpose) {
        Toast.makeText(QuestionsActivity.this,"Oops! An error here", Toast.LENGTH_SHORT).show();
    }

    public void randomNumber(){
        Random rand = new Random();
        randomNum = 1 + rand.nextInt((4 - 1) + 1);
        Log.i("TAG","TAG"+randomNum);

        switch (randomNum){
            case 1:
                Log.i("TAG","TAG");
                mAnswer    =   (TextView) findViewById(R.id.option1);
                break;
            case 2:
                Log.i("TAG","TAG");
                mAnswer    =   (TextView) findViewById(R.id.option2);
                break;
            case 4:
                Log.i("TAG","TAG");
                mAnswer    =   (TextView) findViewById(R.id.option4);
                break;
            default:
                mAnswer    =   (TextView) findViewById(R.id.option3);
                break;
        }
    }
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Progress();
            switch (v.getId()) {
                case R.id.option1:
                    startTimer();
                    Progress();
                    if(randomNum == 1){
                        //mOption1.setBackgroundColor(Color.parseColor("#82ca9c"));
                        mOption1.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption1.getBackground();
                        drawable.setColor(Color.parseColor("#82ca9c"));

                    }
                    else {
                        //mOption1.setBackgroundColor(Color.parseColor("#f49ac1"));
                        mOption1.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption1.getBackground();
                        drawable.setColor(Color.parseColor("#f49ac1"));
                    }
                    randomNumber();
                    break;
                case R.id.option2:
                    startTimer();
                    if(randomNum == 2){
                        //mOption2.setBackgroundColor(Color.parseColor("#82ca9c"));
                        mOption2.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption2.getBackground();
                        drawable.setColor(Color.parseColor("#82ca9c"));
                    }
                    else {
                        //mOption2.setBackgroundColor(Color.parseColor("#f49ac1"));
                        mOption2.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption2.getBackground();
                        drawable.setColor(Color.parseColor("#f49ac1"));
                    }
                    randomNumber();
                    break;
                case R.id.option3:
                    startTimer();
                    if(randomNum == 3){
                        //mOption3.setBackgroundColor(Color.parseColor("#82ca9c"));
                        mOption3.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption3.getBackground();
                        drawable.setColor(Color.parseColor("#82ca9c"));
                    }
                    else {
                        //mOption3.setBackgroundColor(Color.parseColor("#f49ac1"));
                        mOption3.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption3.getBackground();
                        drawable.setColor(Color.parseColor("#f49ac1"));
                    }
                    randomNumber();
                    break;
                case R.id.option4:
                    //Toast.makeText(QuestionsActivity.this, "Option4 has been clicked", Toast.LENGTH_SHORT).show();
                    startTimer();
                    if(randomNum == 4){
                        //mOption4.setBackgroundColor(Color.parseColor("#82ca9c"));
                        mOption4.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption4.getBackground();
                        drawable.setColor(Color.parseColor("#82ca9c"));
                    }
                    else {
                        //mOption4.setBackgroundColor(Color.parseColor("#f49ac1"));
                        mOption4.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption4.getBackground();
                        drawable.setColor(Color.parseColor("#f49ac1"));
                    }
                    randomNumber();
                    break;
                case R.id.play:
                    //Toast.makeText(QuestionsActivity.this, "Play Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QuestionsActivity.this, HurrayActivity.class));
                    mPause.setVisibility(View.VISIBLE);
                    mPlay.setVisibility(View.GONE);
                    break;
                case R.id.pause:
                    //Toast.makeText(QuestionsActivity.this, "Pause Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QuestionsActivity.this, HurrayActivity.class));
                    mPlay.setVisibility(View.VISIBLE);
                    mPause.setVisibility(View.GONE);
                    break;
                case R.id.close:
                    //Toast.makeText(QuestionsActivity.this, "Close Button has been clicked", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(QuestionsActivity.this, MainActivity.class));
                    break;
            }
        }
    }
    private void questionApi(){
        if(Utility.isNetworkAvailable(QuestionsActivity.this)) {
            HttpCalls lHttpCalls = new HttpCalls(QuestionsActivity.this, QuestionsActivity.this);
            lHttpCalls.questionApi(RestConstants.GET_METHOD, RestConstants.QUESTIONS, "QUESTION_API");
        } else {
            Utility.showInternetError(QuestionsActivity.this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        questionApi();
        randomNumber();
        //startTimer();
    }
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        if (timer != null) {
            initializeTimerTask();
        }
        //schedule the timer, after the first 50ms the TimerTask will run every 1000ms
        timer.schedule(timerTask, 2000, 3000); //
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
                        mOption1.setText("Option1");
                        //mOption1.setBackgroundColor(Color.parseColor("#ffffff"));
                        mOption1.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable = (GradientDrawable) mOption1.getBackground();
                        drawable.setColor(Color.parseColor("#ffffff"));

                        mOption2.setText("Option2");
                        //mOption2.setBackgroundColor(Color.parseColor("#ffffff"));
                        mOption2.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable2 = (GradientDrawable) mOption2.getBackground();
                        drawable2.setColor(Color.parseColor("#ffffff"));

                        mOption3.setText("Option3");
                        //mOption3.setBackgroundColor(Color.parseColor("#ffffff"));
                        mOption3.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable3 = (GradientDrawable) mOption3.getBackground();
                        drawable3.setColor(Color.parseColor("#ffffff"));


                        mOption4.setText("Option4");
                        //mOption4.setBackgroundColor(Color.parseColor("#ffffff"));
                        mOption4.setBackgroundResource(R.drawable.border);
                        GradientDrawable drawable4 = (GradientDrawable) mOption4.getBackground();
                        drawable4.setColor(Color.parseColor("#ffffff"));

                        mQuestion.setText(mQuestionArrayList.get(i));
                        mAnswer.setText(mAnswerArrayList.get(i));
                        i++;
                        stoptimertask();
                        try {
                            if (i > 90) {
                                stoptimertask();
                                Toast.makeText(QuestionsActivity.this, "Nore more questions to display. Soon we would be back with more questions!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        };
    }
}
