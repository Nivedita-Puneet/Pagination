package activity.nivedita.com.data;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import activity.nivedita.com.data.network.APIHelper;
import activity.nivedita.com.di.scope.ApplicationContext;
import activity.nivedita.com.model.TopRatedMovies;
import io.reactivex.Flowable;

/**
 * Created by PUNEETU on 12-09-2018.
 */

public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();
    private final APIHelper apiHelper;
    private final Context context;

    @Inject
    public AppDataManager(@ApplicationContext Context context, APIHelper apihelper) {

        this.context = context;
        this.apiHelper = apihelper;
    }

    @Override
    public Flowable<TopRatedMovies> getTopRatedMovies(int currentPage) {
        return apiHelper.getTopRatedMovies(currentPage);
    }

    @Override
    public void onPageLoad() {

    }
}
