package gurug.student.app;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;


/**
 * Created by sandy on 21/3/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}