package com.example.welcome.task.dependencyInjections.module;

import com.example.welcome.task.dependencyInjections.scopes.NewsAppScope;
import com.example.welcome.task.repository.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitServiceModule {

    private static final String API_BASE_URL = "http://www.ctcapitolreport.com/";

    @Provides
    @NewsAppScope
    public HttpLoggingInterceptor getLoggerIntercepter(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NewsAppScope
    public Gson getGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @NewsAppScope
    public OkHttpClient.Builder getOkHttpBuilder(){
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES) ;
    }

    @Provides
    @NewsAppScope
    public Retrofit.Builder getRetrofitBuilder(Gson gson){
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @NewsAppScope
    public ApiService createV4Services(OkHttpClient.Builder httpClient, HttpLoggingInterceptor logging, Retrofit.Builder builder){

        if (! httpClient.interceptors().isEmpty()) {
            httpClient.interceptors().clear() ;
        }

        httpClient.addInterceptor(logging);
        Retrofit retrofit = builder.client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}



