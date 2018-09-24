package activity.nivedita.movies.data;

import javax.inject.Singleton;

import activity.nivedita.movies.data.network.APIHelper;

/**
 * Created by PUNEETU on 08-03-2018.
 */

@Singleton
public interface DataManager extends APIHelper {

    public void onPageLoad();

}
