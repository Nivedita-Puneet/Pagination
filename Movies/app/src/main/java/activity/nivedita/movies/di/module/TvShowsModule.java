package activity.nivedita.movies.di.module;

import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.movies.movies.TVShowsFragment;
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
    LinearLayoutManager linearLayoutManager(TVShowsFragment tvSHowsFragment) {
        return new LinearLayoutManager(tvSHowsFragment.getActivity(),
                LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    TvShowsBasePresenter<TVShowsView> provideTVShowspresenter(TVShowsPresenter<TVShowsView> tvShowsPresenter) {
        return tvShowsPresenter;
    }

}
