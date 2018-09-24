package activity.nivedita.movies.di.module;

import android.content.Context;

import activity.nivedita.movies.di.scope.ActivityContext;
import activity.nivedita.movies.movies.MainActivity;
import activity.nivedita.movies.movies.MoviePagerAdapter;
import activity.nivedita.movies.movies.presenter.MainActivityBasePresenter;
import activity.nivedita.movies.movies.presenter.MainActivityPresenter;
import activity.nivedita.movies.movies.view.MainMVPView;
import dagger.Module;
import dagger.Provides;

/**
 * New Module to be defined to inject to the View Pager activity.
 */

@Module
public class MainActivityModule {

    @Provides
    @ActivityContext
    Context provideContext(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MoviePagerAdapter provideMoviePagerAdapter(MainActivity mainActivity) {

        return new MoviePagerAdapter(mainActivity.getSupportFragmentManager());
    }

    @Provides
    MainActivityBasePresenter<MainMVPView> providePresenter(MainActivityPresenter<MainMVPView> mainActivityPresenter) {
        return mainActivityPresenter;
    }

}
