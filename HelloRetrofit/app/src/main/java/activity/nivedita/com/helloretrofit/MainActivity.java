package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * Main Activity with the View pager.
 */

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();
    }

    private void initializeControls() {

        toolbar = (Toolbar) findViewById(R.id.tabanim_tabs);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        // setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    //TODO: steps to initialize and inject fragment to activity
    /*
    * 1.Create an Activity Builder with dagger annotation introduced with 2.11
    * @ContributesAndroidInjector for PopularMovieFragmentProvider and TVshowsProvider
    * and inject them to MainActivityModule.class
    * 2. The MainActivityModule class exhibits the following Inject the pager adapter and inject the
    * presenter to this class.
    * 3. Define a fragment provider class for popular movies which depends on PopularMoviesModule
    * 4. Define the dependencies for popular Movies module which includes injecting presenter , layout manager, and adapter
    * 5. Then finally define your MainActivity and changes*/

}
