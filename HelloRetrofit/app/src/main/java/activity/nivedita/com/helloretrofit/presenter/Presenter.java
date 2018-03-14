package activity.nivedita.com.helloretrofit.presenter;

import activity.nivedita.com.helloretrofit.view.MVPView;

/**
 * Created by PUNEETU on 08-03-2018.
 */

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();
}
