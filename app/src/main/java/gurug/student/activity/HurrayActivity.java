package gurug.student.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import gurug.student.R;

/**
 * Created by moin on 29/9/16.
 */
public class HurrayActivity extends AppCompatActivity{
    private Button mShare, mContinue;
    private TextView mLevel1, mLevel2;
    private int sum1 =0;
    Uri picUri; int width = 5; int height = 5;

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
        mLevel1      =   (TextView)findViewById(R.id.level1);
        mLevel2      =   (TextView)findViewById(R.id.level2);

        sum1 = MathsActivity.sum - 1;
        mLevel1.setText(sum1+"");
        mLevel2.setText(MathsActivity.sum+"");

        mShare.setOnClickListener(new Listener());
        mContinue.setOnClickListener(new Listener());
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.share:
                    Toast.makeText(HurrayActivity.this, "Share Button has been clicked", Toast.LENGTH_SHORT).show();

                    //performCrop();
                    String value = "Hey, I am sharing this!";
                    Intent intent_sms = new Intent(Intent.ACTION_SEND);
                    intent_sms.putExtra(Intent.EXTRA_TEXT, value);
                    intent_sms.setType("text/plain");
                    startActivity(intent_sms);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
                    break;

                case R.id.continu:
                    Toast.makeText(HurrayActivity.this, "Continue Button has been clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HurrayActivity.this, ExitActivity.class));
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
                    break;
            }
        }
    }
    public void performCrop(){
        try {
            int height1, width1;
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            height1 = size.y;
            width1 = size.x;


            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");

            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", width1*5);
            cropIntent.putExtra("aspectY", height1*5);
            //indicate  X and Y
            cropIntent.putExtra("outputX", width);
            cropIntent.putExtra("outputY", height);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult

            startActivityForResult(cropIntent, 1);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
