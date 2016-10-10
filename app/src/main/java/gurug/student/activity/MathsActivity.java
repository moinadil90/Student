package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import gurug.student.R;

/**
 * Created by moin on 29/9/16.
 */
public class MathsActivity extends AppCompatActivity{
    private Button mPlay, mChallenge;
    private RelativeLayout mRelativeLayout;
    private TextView mSubjectName, mSubjectName1;
    private TextView mLevel1, mLevel2, mLevel3;
    private TextView mPoints, mRequiredPoints, mPoints1;
    private int mStorePoints = 0;
    private int sum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        mPlay           =   (Button)findViewById(R.id.play);
        mChallenge      =   (Button)findViewById(R.id.challenge);
        mRelativeLayout =   (RelativeLayout)findViewById(R.id.rel1);
        mSubjectName    =   (TextView)findViewById(R.id.subjectName);
        mSubjectName1   =   (TextView) findViewById(R.id.subjectName1);
        mLevel1         =   (TextView)findViewById(R.id.level1);
        mLevel2         =   (TextView)findViewById(R.id.level2);
        mPoints         =   (TextView)findViewById(R.id.point_value);
        mLevel3         =   (TextView) findViewById(R.id.level_value);
        mPoints1        =   (TextView) findViewById(R.id.points_value);
        mRequiredPoints =   (TextView)findViewById(R.id.text4);

        mPlay.setOnClickListener(new Listener());
        mChallenge.setOnClickListener(new Listener());

       /* if((SQLite.select().from(Subject.class).where(Subject_Table.color.eq("#6dcff6")).queryList().size()>0)){
       //if(!(SQLite.select().from(Subject.class).where(Subject_Table.subjectName.eq("MATHS")).queryList().size()>0)){
       }*/

        mSubjectName.setText(getIntent().getStringExtra("subject"));
        mSubjectName1.setText(getIntent().getStringExtra("subject"));
        sum = sum + Integer.parseInt((getIntent().getStringExtra("level")));
        mLevel1.setText(getIntent().getStringExtra("level"));
        mLevel2.setText(sum+"");
        mLevel3.setText(getIntent().getStringExtra("level"));
        mPoints.setText(getIntent().getStringExtra("points"));
        mPoints1.setText(getIntent().getStringExtra("points"));
        Log.i("Points", "Points"+ (500 - Integer.parseInt(getIntent().getStringExtra("points"))));
        mStorePoints =  mStorePoints + (500 - Integer.parseInt(getIntent().getStringExtra("points")));
        Log.i("Points1", "Points1"+mStorePoints);
        mRequiredPoints.setText(mStorePoints+"");
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play:
                    //Toast.makeText(MathsActivity.this, "Play Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MathsActivity.this, GameStartsActivity.class));
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
                    break;

                case R.id.challenge:
                    Toast.makeText(MathsActivity.this, "Challenge Button has been clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
