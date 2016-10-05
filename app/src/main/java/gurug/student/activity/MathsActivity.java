package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import gurug.student.R;

/**
 * Created by moin on 29/9/16.
 */
public class MathsActivity extends AppCompatActivity{
    private Button mPlay, mChallenge;
    private RelativeLayout mRelativeLayout;

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

        mPlay.setOnClickListener(new Listener());
        mChallenge.setOnClickListener(new Listener());

       /* if((SQLite.select().from(Subject.class).where(Subject_Table.color.eq("#6dcff6")).queryList().size()>0)){
       //if(!(SQLite.select().from(Subject.class).where(Subject_Table.subjectName.eq("MATHS")).queryList().size()>0)){
       }*/
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play:
                    //Toast.makeText(MathsActivity.this, "Play Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MathsActivity.this, GameStartsActivity.class));
                    break;

                case R.id.challenge:
                    Toast.makeText(MathsActivity.this, "Challenge Button has been clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
