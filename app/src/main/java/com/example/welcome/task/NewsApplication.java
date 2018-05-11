package com.example.welcome.task;

import android.app.Application;

import com.example.welcome.task.dependencyInjections.components.DaggerNewsAppComponent;
import com.example.welcome.task.dependencyInjections.components.NewsAppComponent;
import com.example.welcome.task.dependencyInjections.module.RetrofitServiceModule;

public class NewsApplication extends Application {

    NewsAppComponent newsAppComponent;

    public static NewsApplication APPINSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        APPINSTANCE = this;
        newsAppComponent = DaggerNewsAppComponent.builder()
                .retrofitServiceModule(new RetrofitServiceModule())
                .build();
        newsAppComponent.injectNewsApp(APPINSTANCE);
    }

    public static NewsApplication getAPPINSTANCE(){
        return APPINSTANCE;
    }
    public NewsAppComponent getNewsAppComponent() {
        return newsAppComponent;
    }
}
