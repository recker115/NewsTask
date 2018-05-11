package com.example.welcome.task.repository;

import com.example.welcome.task.repository.model.NewsFeedModel;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/feeds/get_headlines.php?include=blogger&amp;limit=30&amp;sortby=title&amp;order=a&amp;format=json")
    Flowable<NewsFeedModel> getNewsFeeds();
}
