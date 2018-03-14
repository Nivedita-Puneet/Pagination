package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import activity.nivedita.com.base.HelloRetrofitApplication;
import activity.nivedita.com.di.component.ActivityComponent;
import activity.nivedita.com.di.component.DaggerActivityComponent;
import activity.nivedita.com.di.module.ActivityModule;

/**
 * Created by NEETU on 09-03-2018.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    boolean requestOnWay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this, requestOnWay))
                    .applicationComponent(HelloRetrofitApplication.get(this).getComponent()).build();
        }
        return mActivityComponent;
    }

}
