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

 public class MoviePagination extends RecyclerView.OnScrollListener {

     @Inject
    Paginateutil paginateutil;
    /*Handle on scroll items to load more items into recyclerview*/
    private LinearLayoutManager linearLayoutManager;

    private int visibleThreshold = 5;
    private int currentPage;

    private boolean loading = true;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 1;

    public MoviePagination(RecyclerView.LayoutManager layoutManager) {

        this.linearLayoutManager = (LinearLayoutManager) layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        super.onScrolled(recyclerView, dx, dy);

        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        int totalItemCount = linearLayoutManager.getItemCount();

    }


}
