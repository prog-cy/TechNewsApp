package com.android.rupesh.mynewsfeedapp.newsapp.rest;

import com.android.rupesh.mynewsfeedapp.newsapp.model.HomepageModel;
import com.android.rupesh.mynewsfeedapp.newsapp.model.OurYtModel;
import com.android.rupesh.mynewsfeedapp.newsapp.model.YtModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    //Retrofit interface
    //JSON -->GSON library --> Java Object
    @GET("homepage_api")
    Call<HomepageModel> getHomePageApi(@QueryMap Map<String, String> params);

    // Getting news by id
    // Displaying news details using news id
    @GET("news_by_pid")
    Call<HomepageModel> getNewsDetailsById(@QueryMap Map<String, String> params);

    //for Youtube api
    // Getting Youtube details
    @GET("youtube")
    Call<OurYtModel> getYoutubeDetailsFromServer();

    // Connecting to cloud console
    // Check the documentations on  youtube api 3  for the link.
    @GET("https://www.googleapis.com/youtube/v3/activities/")
    Call<YtModel> getYoutubeServerData(@QueryMap Map<String,String> params);

}
