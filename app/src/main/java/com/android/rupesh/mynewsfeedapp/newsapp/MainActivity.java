package com.android.rupesh.mynewsfeedapp.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rupesh.mynewsfeedapp.newsapp.activities.YoutubeActivity;
import com.android.rupesh.mynewsfeedapp.newsapp.adapters.GridCategoryAdapter;
import com.android.rupesh.mynewsfeedapp.newsapp.adapters.NewsAdapter;
import com.android.rupesh.mynewsfeedapp.newsapp.model.HomepageModel;
import com.android.rupesh.mynewsfeedapp.newsapp.rest.ApiClient;
import com.android.rupesh.mynewsfeedapp.newsapp.rest.ApiInterface;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SliderLayout sliderLayout;
    GridView gridView;
    GridCategoryAdapter gridCategoryAdapter;

    //Recycler view to show news from wordpress website.
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<HomepageModel.News> news;
    List<HomepageModel.CategoryBotton> categoryBottons;
    TextView textTV;


    //Variable for making infinite news feed
    int posts = 3;
    int page = 1;
    boolean isFromStart = true;

    //Progressbar
    ProgressBar progressBar;

    //NestedScrollingView
    NestedScrollView nestedScrollView;

    //Swipe refresh layout
    SwipeRefreshLayout swipeRefreshLayout;

    //Floating button
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateViews();

        //Getting data from api
        getHomeData();

        //Initial conditions
        page = 1;
        isFromStart = true;

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())){
                    isFromStart = false;
                    progressBar.setVisibility(View.VISIBLE);
                    page++;
                    getHomeData();
                }
            }
        });
    }

    private void getHomeData() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Map<String, String> params = new HashMap<>();
        params.put("page", page+"");
        params.put("posts", posts+"");

        Call<HomepageModel> call = apiInterface.getHomePageApi(params);
        call.enqueue(new Callback<HomepageModel>() {
            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {
                textTV.setVisibility(View.INVISIBLE);
                updateDataOnHomePage(response.body());
            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Unable to fetch data from server", Toast.LENGTH_SHORT).show();
                textTV.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void updateDataOnHomePage(HomepageModel body) {

        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);

        if(isFromStart){
            news.clear();
            categoryBottons.clear();
        }
        //For adding banners to slider.
        for(int i = 0; i<body.getBanners().size(); i++){

            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.setRequestOption(new RequestOptions().centerCrop());
            defaultSliderView.image(body.getBanners().get(i).getImage());
            defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {

                }
            });

            sliderLayout.addSlider(defaultSliderView);
        }

        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(3000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

        int beforeNewsSize = news.size();
        //For adding news to recycler view
        news.addAll(body.getNews());
        //For adding category image.
        categoryBottons.addAll(body.getCategoryBotton());

        if(isFromStart){
            recyclerView.setAdapter(newsAdapter);
            gridView.setAdapter(gridCategoryAdapter);
        }else{
            newsAdapter.notifyItemRangeInserted(beforeNewsSize, body.getNews().size());
        }

    }

    @SuppressLint("ResourceAsColor")
    private void initiateViews() {

        textTV = findViewById(R.id.toShowNetworkError);
        textTV.setVisibility(View.INVISIBLE);

        //initializing floating action button
        fab = findViewById(R.id.floatings);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(i);
            }
        });

        sliderLayout = findViewById(R.id.img_slider);
        gridView = findViewById(R.id.grid_view);
        categoryBottons = new ArrayList<>();
        gridCategoryAdapter = new GridCategoryAdapter(this, categoryBottons);

        //Initializing recycler view
        recyclerView = findViewById(R.id.recycler_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        news = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, news);

        progressBar = findViewById(R.id.progressBar);

        nestedScrollView = findViewById(R.id.nested);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(R.color.orange, R.color.blue, R.color.green);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }

    @Override
    public void onRefresh() {
        isFromStart = true;
        page = 1;
        getHomeData();
    }
}