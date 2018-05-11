package com.example.welcome.task.dependencyInjections.components;

import com.example.welcome.task.NewsApplication;
import com.example.welcome.task.dependencyInjections.module.RetrofitServiceModule;
import com.example.welcome.task.dependencyInjections.scopes.NewsAppScope;
import com.example.welcome.task.repository.ApiService;

import dagger.Component;

@NewsAppScope
@Component(modules = RetrofitServiceModule.class)
public interface NewsAppComponent {
    void injectNewsApp(NewsApplication newsApplication);
    ApiService getApiService();
}
