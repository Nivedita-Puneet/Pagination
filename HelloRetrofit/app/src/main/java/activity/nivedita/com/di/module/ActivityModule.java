package activity.nivedita.com.di.module;

import android.app.Activity;
import android.content.Context;

import activity.nivedita.com.di.scope.ActivityContext;
import activity.nivedita.com.helloretrofit.MovieAdapter;
import dagger.Module;
import dagger.Provides;
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
    PublishProcessor<Integer> getPublishProcessor() {
        return PublishProcessor.create();
    }
}
