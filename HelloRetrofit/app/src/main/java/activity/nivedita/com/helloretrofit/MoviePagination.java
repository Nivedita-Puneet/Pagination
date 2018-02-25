package activity.nivedita.com.helloretrofit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by PUNEETU on 24-02-2018.
 */

abstract public class MoviePagination extends RecyclerView.OnScrollListener {

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

        if (totalItemCount < previousTotalItemCount) {

            MoviePagination.this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {

            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
            loading = true;
        }
    }

    abstract public void onLoadMore(int currentPage, int totalItemCount, View view);

}
