package activity.nivedita.com.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.com.di.scope.ActivityContext;
import activity.nivedita.com.di.scope.PerActivity;
import activity.nivedita.com.helloretrofit.MovieAdapter;
import activity.nivedita.com.helloretrofit.MovieView;
import activity.nivedita.com.helloretrofit.presenter.MoviesBasePresenter;
import activity.nivedita.com.helloretrofit.presenter.MoviesPresenter;
import activity.nivedita.com.networkutils.rx.AppSchedulerprovider;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by NEETU on 08-03-2018.
 */

@Module
public class ActivityModule {

    private Activity mActivity;
    private boolean isRequestOnWay;

    public ActivityModule(Activity activity, boolean isRequestOnWay) {

        this.mActivity = activity;
        this.isRequestOnWay = isRequestOnWay;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    MovieAdapter provideMoviesAdapter() {
        return new MovieAdapter(mActivity);
    }

    @Provides
    boolean isRequestOnWay() {
        return isRequestOnWay;
    }

    @Provides
    LinearLayoutManager linearLayoutManager() {
        return new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    int getCurrentPage() {
        return 1;
    }

    @Provides
    boolean getLoading() {
        return false;
    }

    @Provides
    PublishProcessor<Integer> publishProcessor() {
        return PublishProcessor.create();
    }

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerprovider();
    }

    @Provides
    @PerActivity
    MoviesBasePresenter<MovieView> provideSunshinePresenter(MoviesPresenter<MovieView> moviesPresenter) {
        return moviesPresenter;
    }


}
