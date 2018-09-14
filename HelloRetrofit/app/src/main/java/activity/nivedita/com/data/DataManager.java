package activity.nivedita.com.data;

import android.util.Log;

import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

import activity.nivedita.com.data.network.APIHelper;
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
public interface DataManager extends APIHelper {

    public void onPageLoad();

}
