
package activity.nivedita.movies.model.tvshows;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShows {

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
    private List<PopularTvShows> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public TvShows withPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public TvShows withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public TvShows withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public List<PopularTvShows> getResults() {
        return results;
    }

    public void setResults(List<PopularTvShows> results) {
        this.results = results;
    }

    public TvShows withResults(List<PopularTvShows> results) {
        this.results = results;
        return this;
    }

}
