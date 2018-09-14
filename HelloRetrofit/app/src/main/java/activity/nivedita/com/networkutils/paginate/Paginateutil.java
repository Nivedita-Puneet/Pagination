package activity.nivedita.com.networkutils.paginate;

import javax.inject.Inject;

import io.reactivex.processors.PublishProcessor;

/**
 * Utilities created for pagination
 */

public class Paginateutil {


    private int pageNumber;
    private boolean loading;

    public Paginateutil(int pageNumber, boolean loading) {
        this.pageNumber = pageNumber;
        this.loading = loading;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }


}
