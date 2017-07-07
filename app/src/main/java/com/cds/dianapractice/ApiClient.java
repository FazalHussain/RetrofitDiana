package com.cds.dianapractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fazal on 7/7/2017.
 */

public interface ApiClient {

    @GET("cities/cities/")
    public Call<TodosModel> getList();


    @GET("cities/cities/")
    public Call<TodosModel> getFilterList(@Query("state") String state);
}
