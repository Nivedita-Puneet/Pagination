package activity.nivedita.movies.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import activity.nivedita.movies.base.BaseFragment;
import activity.nivedita.movies.movies.presenter.MoviesBasePresenter;
import activity.nivedita.movies.model.TopRatedMovies;
import activity.nivedita.movies.networkutils.LogNetworkError;
import dagger.android.support.AndroidSupportInjection;

/**
 * Fragment to be attached in the first tab
 */

public class MoviesFragment extends BaseFragment implements MovieView {

    @Inject
    MoviesBasePresenter<MovieView> moviesPresenter;

    @Inject
    MovieAdapter movieAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    private int lastVisibleItem, totalItemCount;

    private final int VISIBLE_THRESHOLD = 1;

    public static MoviesFragment newInstance() {

        Bundle args = new Bundle();
        MoviesFragment moviesFragment = new MoviesFragment();
        moviesFragment.setArguments(args);
        return moviesFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(MoviesFragment.this);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        moviesPresenter.attachView(this);
        View view = inflater.inflate(R.layout.activity_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        setUpFragment();
        return view;
    }

    private void setUpFragment() {

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition();
                if (!moviesPresenter.getLoading() &&
                        (totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD))) {

                    int pageIndex = moviesPresenter.getCurrentPage();
                    pageIndex++;
                    moviesPresenter.setCurrentPage(pageIndex);

                    moviesPresenter.getPublishProcessor().onNext(pageIndex);
                    //movieAdapter.addLoadingFooter();
                    moviesPresenter.setLoading(true);
                }


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        moviesPresenter.onViewInitialized();
    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

        Toast.makeText(getActivity(), logNetworkError.getAppErrorMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void showWait() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getMoviesListSuccess(TopRatedMovies response) {

        movieAdapter.addAll(response.getResults());
    }

    @Override
    public void noMoviesToDisplay() {

    }

    @Override
    public void addLoadingFooter(TopRatedMovies response) {

    }

    @Override
    public void removeLoadingFooter(TopRatedMovies response) {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        moviesPresenter.detachView();
    }
}
