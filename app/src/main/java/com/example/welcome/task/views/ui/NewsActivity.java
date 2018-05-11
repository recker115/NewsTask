package com.example.welcome.task.views.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.welcome.task.R;
import com.example.welcome.task.repository.model.EachItem;
import com.example.welcome.task.viewModel.NewsActivityViewModel;
import com.example.welcome.task.views.adapters.NewsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements LifecycleOwner {

    NewsActivityViewModel newsActivityViewModel;
    Observer<List<EachItem>> newsItemsObserver;
    MutableLiveData<List<EachItem>> mutableLiveData;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    NewsAdapter rvNewsAdapter;

    List<EachItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        initMembers();
    }

    private void initMembers() {
        newsItems = new ArrayList<>();
        rvNewsAdapter = new NewsAdapter(this,newsItems);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(rvNewsAdapter);

        newsActivityViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel.class);
        newsItemsObserver = newsItems -> rvNewsAdapter.addItems(newsItems);

        mutableLiveData = newsActivityViewModel.getNewsItemsLiveData();
        observeLiveData();

    }

    public void observeLiveData(){
        mutableLiveData.observe(this,newsItemsObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observeLiveData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mutableLiveData.removeObserver(newsItemsObserver);
    }
}
