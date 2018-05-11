package com.example.welcome.task.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.welcome.task.R;
import com.example.welcome.task.repository.model.EachItem;
import com.example.welcome.task.views.ui.WebLinkActivity;
import com.example.welcome.task.views.viewHolders.NewsViewHolder;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<EachItem> newsItems;
    private Context context;

    public NewsAdapter(Context context, List<EachItem> newsItems){
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        EachItem eachItem = newsItems.get(position);
        String[] splittedString = eachItem.getPubDate().split(" ");
        String dateWithoutTime = splittedString[0]+" "+splittedString[1]+" "+splittedString[2]+" "+splittedString[3];
        String date = "Date "+dateWithoutTime;

        holder.tvDate.setText(date);
        holder.tvLink.setText(eachItem.getLink());
        holder.tvTitle.setText(eachItem.getTitle());
        holder.tvGuid.setText(eachItem.getGuid());

        holder.rootView.setOnClickListener(v -> {
            Intent webIntent = new Intent(context, WebLinkActivity.class);
            webIntent.putExtra("link",eachItem.getLink());
            context.startActivity(webIntent);
        });

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public void addItems(List<EachItem> newsItems){
        this.newsItems.addAll(newsItems);
        notifyItemRangeInserted(0,this.newsItems.size()-1);
    }
}
