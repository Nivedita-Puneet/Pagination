package activity.nivedita.movies.data;

import android.content.Context;

import javax.inject.Inject;

import activity.nivedita.movies.data.network.APIHelper;
import activity.nivedita.movies.di.scope.ApplicationContext;
import activity.nivedita.movies.model.TopRatedMovies;
import activity.nivedita.movies.model.tvshows.TvShows;
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
    public Flowable<TvShows> getPopularTVShows(int currentPage) {
        return apiHelper.getPopularTVShows(currentPage);
    }

    @Override
    public void onPageLoad() {

    }
}
