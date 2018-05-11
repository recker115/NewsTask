package com.example.welcome.task.repository.NewsRepository;

import android.arch.lifecycle.MutableLiveData;

import com.example.welcome.task.NewsApplication;
import com.example.welcome.task.dependencyInjections.components.DaggerNewsRepoComponent;
import com.example.welcome.task.dependencyInjections.components.NewsRepoComponent;
import com.example.welcome.task.repository.ApiService;
import com.example.welcome.task.repository.model.EachItem;
import com.example.welcome.task.repository.model.NewsFeedModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class NewsRespository {

    private MutableLiveData<List<EachItem>> newsFeedModelMutableLiveData;

    @Inject
    public ApiService apiService;

    public NewsRepoComponent newsRepoComponent;

    public NewsRespository(){
        newsRepoComponent = DaggerNewsRepoComponent.builder()
                .newsAppComponent(NewsApplication.getAPPINSTANCE().getNewsAppComponent())
                .build();
        newsRepoComponent.injectNewsRepo(this);
        newsFeedModelMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<EachItem>> getAllNewsFeed(){
        performCall();
        return newsFeedModelMutableLiveData;
    }

    private void performCall() {
        Flowable<NewsFeedModel> newsFeedModelObservable = apiService.getNewsFeeds();
        new CompositeDisposable().add(newsFeedModelObservable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribeWith(new ResourceSubscriber<NewsFeedModel>() {
                    @Override
                    public void onNext(NewsFeedModel newsFeedModel) {
                         newsFeedModelMutableLiveData.postValue(newsFeedModel.getChannel().getItems());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
