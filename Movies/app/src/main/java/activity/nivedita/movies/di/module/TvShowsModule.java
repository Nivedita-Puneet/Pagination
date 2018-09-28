package activity.nivedita.movies.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.movies.movies.TVShowsFragment;
import activity.nivedita.movies.movies.TvShowsAdapter;
import activity.nivedita.movies.movies.presenter.TVShowsPresenter;
import activity.nivedita.movies.movies.presenter.TvShowsBasePresenter;
import activity.nivedita.movies.movies.view.TVShowsView;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * The TV shows module to be defined in order to inject into View Pager Adapter.
 */
@Module
public class TvShowsModule {

    @Provides
    PublishProcessor<Integer> publishProcessor() {
        return PublishProcessor.create();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(TVShowsFragment tvSHowsFragment) {
        return new GridLayoutManager(tvSHowsFragment.getActivity(), 2);
    }

    @Provides
    TvShowsAdapter provideTvShowsAdapter(TVShowsFragment tvShowsFragment) {
        return new TvShowsAdapter(tvShowsFragment.getActivity());
    }

    @Provides
    TvShowsBasePresenter<TVShowsView> provideTVShowspresenter(TVShowsPresenter<TVShowsView> tvShowsPresenter) {
        return tvShowsPresenter;
    }

}
