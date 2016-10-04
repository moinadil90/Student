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
public class HurrayActivity extends AppCompatActivity{
    private Button mShare, mContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hurray);
        init();
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        mShare       =   (Button)findViewById(R.id.share);
        mContinue    =   (Button)findViewById(R.id.continu);

        mShare.setOnClickListener(new Listener());
        mContinue.setOnClickListener(new Listener());
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.share:
                    Toast.makeText(HurrayActivity.this, "Share Button has been clicked", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.continu:
                    Toast.makeText(HurrayActivity.this, "Continue Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HurrayActivity.this, ExitActivity.class));
                    break;
            }
        }
    }

}
