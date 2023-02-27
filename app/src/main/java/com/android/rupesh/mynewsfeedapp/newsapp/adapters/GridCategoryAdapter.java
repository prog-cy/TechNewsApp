package com.android.rupesh.mynewsfeedapp.newsapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.rupesh.mynewsfeedapp.newsapp.R;
import com.android.rupesh.mynewsfeedapp.newsapp.model.HomepageModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridCategoryAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    Context context;
    List<HomepageModel.CategoryBotton> demoCategoryList;

    public GridCategoryAdapter(Context context, List<HomepageModel.CategoryBotton> demoCategoryList){
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.demoCategoryList = demoCategoryList;

    }
    @Override
    public int getCount() {
        return demoCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return demoCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){

            convertView = layoutInflater.inflate(R.layout.item_category_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.circleImageView = convertView.findViewById(R.id.category_image);
            viewHolder.textView = convertView.findViewById(R.id.text_category);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(demoCategoryList.get(position).getName());
        Glide.with(context)
                .load(demoCategoryList.get(position).getImage()).into(viewHolder.circleImageView);

        if(demoCategoryList.get(position).getCid() != null){
//            viewHolder.circleImageView.setBackgroundColor(Color.parseColor(demoCategoryList.get(position).getColor()));
            viewHolder.circleImageView.setBorderColor(Color.parseColor(demoCategoryList.get(position).getColor()));
        }

        return convertView;
    }

    //Creating ViewHolder class
    static class ViewHolder{
        CircleImageView circleImageView;
        TextView textView;
    }

}
