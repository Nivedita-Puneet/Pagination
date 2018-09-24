package activity.nivedita.movies.movies.view;

import activity.nivedita.movies.networkutils.LogNetworkError;

/**
 * Created by NEETU on 08-03-2018.
 */

/*A base view which will be implemented by activity or fragment*/
public interface MVPView {

    void showError(LogNetworkError logNetworkError);

    void showWait();

    void removeWait();

}
