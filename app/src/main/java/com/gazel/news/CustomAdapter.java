package com.gazel.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private  Context mContext;
    private  int mResource;
    private List<News> mNews;

    private static class  ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvDescription;
    }

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<News> news) {
        super(context, resource, news);

        mContext = context;
        mResource = resource;
        mNews = news;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null){
            convertView = layoutInflater.inflate(mResource, parent, false);

            viewHolder.ivImage = convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDescription = convertView.findViewById(R.id.tvDescription);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        News news = mNews.get(position);

        Picasso.with(mContext).load(news.getImage()).into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(news.getTitle());
        viewHolder.tvDescription.setText(news.getDescription());

        return convertView;
    }
}
