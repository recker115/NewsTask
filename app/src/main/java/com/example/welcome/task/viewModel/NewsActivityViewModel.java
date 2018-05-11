package com.example.welcome.task.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.welcome.task.repository.NewsRepository.NewsRespository;
import com.example.welcome.task.repository.model.EachItem;

import java.util.List;

public class NewsActivityViewModel extends AndroidViewModel {

    public MutableLiveData<List<EachItem>> newsItemsLiveData;
    public NewsActivityViewModel(@NonNull Application application) {
        super(application);
        newsItemsLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<EachItem>> getNewsItemsLiveData(){
        newsItemsLiveData = new NewsRespository().getAllNewsFeed();
        return newsItemsLiveData;
    }
}
