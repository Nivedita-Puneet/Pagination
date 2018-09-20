package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import activity.nivedita.com.base.BaseFragment;
import dagger.android.AndroidInjection;

/**
 * Created by NEETU on 09-03-2018.
 */

public class BaseActivity extends AppCompatActivity implements BaseFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDependencyInjection();
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

    public void performDependencyInjection(){
        AndroidInjection.inject(BaseActivity.this);
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
