package activity.nivedita.movies.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import activity.nivedita.movies.base.BaseFragment;
import activity.nivedita.movies.model.tvshows.PopularTvShows;
import activity.nivedita.movies.movies.presenter.TvShowsBasePresenter;
import activity.nivedita.movies.movies.view.TVShowsView;
import activity.nivedita.movies.networkutils.LogNetworkError;
import dagger.android.support.AndroidSupportInjection;

/**
 * New Fragment to be attached when attaching the second tab
 */

public class TVShowsFragment extends BaseFragment implements TVShowsView {

    @Inject
    TvShowsBasePresenter<TVShowsView> tvShowsPresenter;

    @Inject
    TvShowsAdapter tvShowsAdapter;

    @Inject
    GridLayoutManager gridLayoutManager;

    RecyclerView mRecyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

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
        tvShowsPresenter.attachView(this);
        initializeControls(view);
        return view;
    }

    private void initializeControls(View view){

        mRecyclerView = (RecyclerView)view.findViewById(R.id.tvShows_recyclerview);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(tvShowsAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setEnabled(false);
                //TODO: Notify data set has been changed to the adpater and bind the data again
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        tvShowsPresenter.onFragmentViewInitialized();
    }

    @Override
    public void viewTVShows() {

    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

        Toast.makeText(getActivity(),
                "Please Review your network settings"+" "+logNetworkError.getAppErrorMessage() ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void displayPopularTvShows(List<PopularTvShows> popularTvShowsList) {

        tvShowsAdapter.addAll(popularTvShowsList);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        tvShowsPresenter.detachView();
    }
}
