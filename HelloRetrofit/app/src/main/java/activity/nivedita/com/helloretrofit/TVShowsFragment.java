package activity.nivedita.com.helloretrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import activity.nivedita.com.base.BaseFragment;
import activity.nivedita.com.helloretrofit.presenter.TvShowsBasePresenter;
import activity.nivedita.com.helloretrofit.view.TVShowsView;
import activity.nivedita.com.networkutils.LogNetworkError;
import dagger.android.support.AndroidSupportInjection;

/**
 * New Fragment to be attached when attaching the second tab
 */

public class TVShowsFragment extends BaseFragment implements TVShowsView {

    @Inject
    TvShowsBasePresenter<TVShowsView> tvShowsPresenter;


    public static TVShowsFragment newInstance() {

        Bundle args = new Bundle();
        TVShowsFragment tvShowsFragment = new TVShowsFragment();
        tvShowsFragment.setArguments(args);
        return tvShowsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(TVShowsFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tvshows_fragment, container, false);
        TextView testTextView = (TextView) view.findViewById(R.id.testId);
        testTextView.setText("Testing Dagger and View Pager");
        tvShowsPresenter.attachView(this);
        return view;
    }


    @Override
    public void viewTVShows() {

    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        tvShowsPresenter.detachView();
    }
}
