package activity.nivedita.com.helloretrofit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import activity.nivedita.com.helloretrofit.presenter.MoviesPresenter;
import activity.nivedita.com.networkutils.paginate.Paginateutil;

/**
 * Created by PUNEETU on 24-02-2018.
 */

public abstract class MoviePagination extends RecyclerView.OnScrollListener {

    /*Handle on scroll items to load more items into recyclerview*/
    private LinearLayoutManager linearLayoutManager;

    public MoviePagination(RecyclerView.LayoutManager layoutManager) {

        this.linearLayoutManager = (LinearLayoutManager) layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }

    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();


}
