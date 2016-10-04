package gurug.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import gurug.student.R;

/**
 * Created by moin on 29/9/16.
 */
public class ExitActivity extends AppCompatActivity{
    private Button mShare, mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        mShare       =   (Button)findViewById(R.id.share);
        mExit        =   (Button)findViewById(R.id.exit);

        mShare.setOnClickListener(new Listener());
        mExit.setOnClickListener(new Listener());
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.share:
                    Toast.makeText(ExitActivity.this, "Share Button has been clicked", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.exit:
                    //Toast.makeText(ExitActivity.this, "Exit Button has been clicked", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ExitActivity.this, SubjectActivity.class));
                    break;
            }
        }
    }

}
