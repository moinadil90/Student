package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import gurug.student.R;
import gurug.student.api.HttpCalls;
import gurug.student.callback.OnTaskCompleted;
import gurug.student.model.Question;
import gurug.student.util.RestConstants;
import gurug.student.util.Utility;

public class GameStartsActivity extends AppCompatActivity implements OnTaskCompleted {
    private Button mNext;
    private TextView mSecond;
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

        mNext   =   (Button) findViewById(R.id.next);
        mSecond =   (TextView) findViewById(R.id.seconds);
        mNext.setOnClickListener(new Listener());
    }

    @Override
    public void onTaskCompleted(String response, String lPurpose) {
        Log.i("onTaskCompleted","onTaskCompleted");
        if (lPurpose.equalsIgnoreCase("QUESTION")) {
            JSONArray lJSJsonArray = null;
            try {
                lJSJsonArray = new JSONArray(response);
                for (int j = 0; j < lJSJsonArray.length(); j++) {
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
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTaskError(String response, String lPurpose) {

    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next:
                    startActivity(new Intent(GameStartsActivity.this, QuestionsActivity.class));
                    //questionApi();
                    break;
            }
        }
    }
    private void questionApi(){
        if(Utility.isNetworkAvailable(GameStartsActivity.this)) {
            HttpCalls lHttpCalls = new HttpCalls(GameStartsActivity.this, GameStartsActivity.this);
            lHttpCalls.questionApi(RestConstants.GET_METHOD, RestConstants.QUESTIONS, "QUESTION_API");
        } else {
            Utility.showInternetError(GameStartsActivity.this);
        }
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

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        //get the current timeStamp
                        /*Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        final String strDate = simpleDateFormat.format(calendar.getTime());*/

                        //show the toast
                       /* int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), "Time: "+i, duration);
                        toast.show();*/
                        mSecond.setText(""+i);
                        i--;
                        if(i==0){
                            stoptimertask();
                            startActivity(new Intent(GameStartsActivity.this, QuestionsActivity.class));
                            //questionApi();
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
