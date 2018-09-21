package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import activity.nivedita.com.helloretrofit.presenter.MainActivityBasePresenter;
import activity.nivedita.com.helloretrofit.view.MainMVPView;
import activity.nivedita.com.model.Result;
import activity.nivedita.com.networkutils.LogNetworkError;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


/**
 * Main Activity with the View pager.
 */

public class MainActivity extends BaseActivity implements
        HasSupportFragmentInjector, MainMVPView {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    MainActivityBasePresenter<MainMVPView> mainActivityPresenter;

    @Inject
    MoviePagerAdapter moviePagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();
    }

    private void initializeControls() {

        toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        /*if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/


        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        // setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);

        setViewPagerAdapter();
        mainActivityPresenter.attachView(MainActivity.this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void setViewPagerAdapter(){

        moviePagerAdapter.setCount(2);
        viewPager.setAdapter(moviePagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText(getText(R.string.popular_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tv_shows)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void showMovies(List<Result> movies) {

    }

    @Override
    public void showMoviesEmpty() {

    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }
}
