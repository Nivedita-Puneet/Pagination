package activity.nivedita.com.di.module;

import android.app.Activity;

import activity.nivedita.com.helloretrofit.MainActivity;
import activity.nivedita.com.helloretrofit.MoviePagerAdapter;
import activity.nivedita.com.helloretrofit.presenter.MainActivityBasePresenter;
import activity.nivedita.com.helloretrofit.presenter.MainActivityPresenter;
import activity.nivedita.com.helloretrofit.view.MainMVPView;
import activity.nivedita.com.networkutils.rx.AppSchedulerprovider;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * New Module to be defined to inject to the View Pager activity.
 */

@Module
public class MainActivityModule {

    private Activity mActivity;


    @Provides
    MoviePagerAdapter provideMoviePagerAdapter(MainActivity mainActivity) {

        return new MoviePagerAdapter(mainActivity.getSupportFragmentManager());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerprovider();
    }

    @Provides
    MainActivityBasePresenter<MainMVPView> providePresenter(MainActivityPresenter mainActivityPresenter) {
        return mainActivityPresenter;
    }

}
