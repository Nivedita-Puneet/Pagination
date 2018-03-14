package activity.nivedita.com.data;

import android.util.Log;

import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.LogNetworkError;
import activity.nivedita.com.networkutils.MovieService;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PUNEETU on 08-03-2018.
 */

@Singleton
public class DataManager {

    private final MovieService movieService;
    public static final String TAG = DataManager.class.getSimpleName();

    @Inject
    public DataManager(MovieService movieService) {
        this.movieService = movieService;
    }


    public Flowable<TopRatedMovies> getListOfTopRatedMovies() {

        return movieService.getTopRatedMovies(ConstantsUtil.TMDB_API_KEY,
                "en_US",
                ConstantsUtil.TOTAL_PAGES);
    }
}
