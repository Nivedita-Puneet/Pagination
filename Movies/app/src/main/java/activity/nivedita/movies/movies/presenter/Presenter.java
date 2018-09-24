package activity.nivedita.movies.movies.presenter;

import activity.nivedita.movies.movies.view.MVPView;

/**
 * Created by PUNEETU on 08-03-2018.
 */

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();
}
