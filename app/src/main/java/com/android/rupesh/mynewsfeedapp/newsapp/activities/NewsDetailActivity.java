package com.android.rupesh.mynewsfeedapp.newsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rupesh.mynewsfeedapp.newsapp.R;
import com.android.rupesh.mynewsfeedapp.newsapp.adapters.NewsAdapter;
import com.android.rupesh.mynewsfeedapp.newsapp.model.HomepageModel;
import com.android.rupesh.mynewsfeedapp.newsapp.rest.ApiClient;
import com.android.rupesh.mynewsfeedapp.newsapp.rest.ApiInterface;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView sourceName, newsTitle, newsDesc, newsDate, newsView,labelSimilar;
    Button viewMore;
    ImageView imagy,small_icn;


    ProgressBar progressBar;

    HomepageModel.News detailNews = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initViews();

        LoadNewsDetails();

    }

    private void LoadNewsDetails() {

        // Calling our api
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Map<String, String> params = new HashMap<>();

        params.put("id" , getIntent().getIntExtra("pid", 0) + "");


        Call<HomepageModel> call = apiInterface.getNewsDetailsById(params);
        call.enqueue(new Callback<HomepageModel>() {
            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {

                // Update the news layout
                assert response.body() != null;
                detailNews = response.body().getNews().get(0);
                newsTitle.setText(NewsAdapter.removeHtml(detailNews.getTitle()).trim());
                newsDesc.setText(NewsAdapter.removeHtml(detailNews.getPostContent()).trim());
                newsDate.setText(detailNews.getPostDate());
                sourceName.setText(detailNews.getSource());

                //This if else is responsible for show image and icon.
                if (detailNews.getImage().length() >=1){
                    Glide.with(NewsDetailActivity.this)
                            .load(detailNews.getImage())
                            .into(imagy);

                    Glide.with(NewsDetailActivity.this)
                            .load(detailNews.getImage())
                            .into(small_icn);
                }else{
                    imagy.setVisibility(View.GONE);
                }

                //View More
                // This will direct us to the source url from where we have taken our news.
                viewMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newsUrl = "";
                        if (detailNews.getSourceUrl() != null){
                            newsUrl = detailNews.getSourceUrl();
                        }else{
                            newsUrl = detailNews.getUrl();
                        }

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl));
                        startActivity(browserIntent);
                    }
                });



            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void initViews() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail News");
        toolbar.setNavigationIcon(R.drawable.icon_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sourceName = findViewById(R.id.source_name );
        newsTitle  = findViewById(R.id.news_title);
        newsDesc  = findViewById(R.id.news_desc);
        newsDate = findViewById(R.id.news_date);
        newsView = findViewById(R.id.news_view);
        labelSimilar = findViewById(R.id.label_similar_news);

        viewMore = findViewById(R.id.view_more);
        progressBar = findViewById(R.id.progressBar);

        imagy =findViewById(R.id.news_image);
        small_icn = findViewById(R.id.small_icn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.news_share_icon){
            if(detailNews != null) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, NewsAdapter.removeHtml(detailNews.getTitle()).trim());
                intent.putExtra(Intent.EXTRA_TEXT, NewsAdapter.removeHtml(detailNews.getPostContent()).trim());
                startActivity(intent);
            }else{
                Toast.makeText(this, "Unable to share some error occurred", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}