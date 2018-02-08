package org.nivi.mydailynews.restapi;

import org.nivi.mydailynews.model.Category;
import org.nivi.mydailynews.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by NEETU on 07-02-2018.
 */

/*Interface used to retrieve News object*/
public interface NewsAPI {

    @GET("2iodh4vg0eortkl/facts.json")
    Call<News> getUpdatedNews();
}