package com.example.welcome.task.views.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.welcome.task.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitle)
    public TextView tvTitle;

    @BindView(R.id.tvDate)
    public TextView tvDate;

    @BindView(R.id.tvGuid)
    public TextView tvGuid;

    @BindView(R.id.tvLink)
    public TextView tvLink;

    @BindView(R.id.rootView)
    public View rootView;

    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
