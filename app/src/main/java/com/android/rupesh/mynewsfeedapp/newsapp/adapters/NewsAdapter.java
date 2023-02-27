package com.android.rupesh.mynewsfeedapp.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rupesh.mynewsfeedapp.newsapp.R;
import com.android.rupesh.mynewsfeedapp.newsapp.activities.NewsDetailActivity;
import com.android.rupesh.mynewsfeedapp.newsapp.model.HomepageModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    Context context;
    List<HomepageModel.News> news;
    int image_left = 1;
    int image_top = 2;

    //Constructor
    public NewsAdapter(Context context, List<HomepageModel.News> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public int getItemViewType(int position) {

        //This method will help to load two different layout in recycler view.
        if((position+1)%8 == 0 || position == 0){

            //This logic is to show big image layout after every 8 small layout.
            return image_top;
        }

        return image_left;
    }

    //Custom view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView newsImage;
        TextView newsTitle, newsDesc, newsDate, newsSource, newsView;

        View holder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holder = itemView;
            newsImage = itemView.findViewById(R.id.news_image);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsDesc = itemView.findViewById(R.id.news_desc);
            newsDate = itemView.findViewById(R.id.news_date);
            newsSource = itemView.findViewById(R.id.news_source);
            newsView = itemView.findViewById(R.id.news_view);
            holder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra("pid", news.get(getAdapterPosition()).getPid());
            context.startActivity(intent);
        }
    }

    // Removing HTML Codes
    public static String removeHtml(String html){
        html = html.replaceAll("<(.*?)\\>"," ");//Removes all items in brackets
        html = html.replaceAll("<(.*?)\\\n"," ");//Must be underneath
        html = html.replaceFirst("(.*?)\\>", " ");//Removes any connected item to the last bracket
        html = html.replaceAll("&nbsp;"," ");
        html = html.replaceAll("&amp;"," ");
        return html;
        // Check the description and the source code if you want it
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == image_left){

            View v = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            return new ViewHolder(v);
        }else{

            View v = LayoutInflater.from(context).inflate(R.layout.item_news_image, parent, false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomepageModel.News singleNews = news.get(holder.getAdapterPosition());
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.newsTitle.setText(removeHtml(singleNews.getTitle()).trim());
        viewHolder.newsDesc.setText(removeHtml(singleNews.getPostContent()).trim());
        viewHolder.newsDate.setText(singleNews.getPostDate());

        if(singleNews.getSource() != null){
            viewHolder.newsSource.setText(removeHtml(singleNews.getSource()));
        }

        if(singleNews.getImage().length() <= 1){
            viewHolder.newsImage.setVisibility(View.GONE);
        }else{
            viewHolder.newsImage.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(singleNews.getImage())
                    .into(viewHolder.newsImage);
        }

    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
