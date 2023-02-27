package com.android.rupesh.mynewsfeedapp.newsapp.rest;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient extends AppCompatActivity {

    //Local Host for emulators
    //10.0.2.2 default local host for emulators in android studio
    public static final String BASE_URL = "http://10.0.2.2/newsapp/wp-json/api/";

    //For real devices we need to get ip address
    //1- Connect your laptop and mobile on the same wifi
    //2- find local ip address of your laptop
    //3- Add that local IP address in your BASE_URL variable

    //Always need to update ip address when opening pc and connecting with different wi-fi.
    public static final String BASE_URL_REAL = "http://192.168.18.7/newsapp/wp-json/api/";


    //Getting the retrofit api client
    private static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_REAL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
