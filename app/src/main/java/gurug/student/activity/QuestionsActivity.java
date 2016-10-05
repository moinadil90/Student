package gurug.student.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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
    private ImageButton mPlay, mPause, mClose;
    public static ArrayList<Question> mQuestionArrayList;

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

        mOption1   =   (TextView) findViewById(R.id.option1);
        mOption2   =   (TextView) findViewById(R.id.option2);
        mOption3   =   (TextView) findViewById(R.id.option3);
        mOption4   =   (TextView) findViewById(R.id.option4);
        mPlay      =   (ImageButton) findViewById(R.id.play);
        mPause     =   (ImageButton) findViewById(R.id.pause);
        mClose     =   (ImageButton) findViewById(R.id.close);
        mQuestion  =   (TextView) findViewById(R.id.question);

        mOption1.setOnClickListener(new Listener());
        mOption2.setOnClickListener(new Listener());
        mOption3.setOnClickListener(new Listener());
        mOption4.setOnClickListener(new Listener());

        mPlay.setOnClickListener(new Listener());
        mPause.setOnClickListener(new Listener());
        mClose.setOnClickListener(new Listener());
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
        Toast.makeText(QuestionsActivity.this,"Oops! An error here", Toast.LENGTH_SHORT).show();
    }
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.option1:
                    Toast.makeText(QuestionsActivity.this, "Option1 has been clicked", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MathsActivity.this, GameStartsActivity.class));
                    break;
                case R.id.option2:
                    //Toast.makeText(QuestionsActivity.this, "Option2 has been clicked", Toast.LENGTH_SHORT).show();
                    mOption2.setBackgroundColor(Color.parseColor("#f49ac1"));
                    mOption3.setBackgroundColor(Color.parseColor("#82ca9c"));
                    break;
                case R.id.option3:
                    Toast.makeText(QuestionsActivity.this, "Option3 has been clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.option4:
                    Toast.makeText(QuestionsActivity.this, "Option4 has been clicked", Toast.LENGTH_SHORT).show();
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
    }
}
