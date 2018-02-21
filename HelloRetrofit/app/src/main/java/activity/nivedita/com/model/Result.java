package activity.nivedita.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NEETU on 21-02-2018.
 *
 * Object used to hold the Total results or pages while using pagination
 */

public class Result {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<TopRatedMovies> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Result withPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Result withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Result withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public List<TopRatedMovies> getResults() {
        return results;
    }

    public void setResults(List<TopRatedMovies> results) {
        this.results = results;
    }

    public Result withResults(List<TopRatedMovies> results) {
        this.results = results;
        return this;
    }

}
