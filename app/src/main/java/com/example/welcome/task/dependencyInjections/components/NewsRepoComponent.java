package com.example.welcome.task.dependencyInjections.components;

import com.example.welcome.task.dependencyInjections.scopes.NewsRepoScope;
import com.example.welcome.task.repository.NewsRepository.NewsRespository;

import dagger.Component;

@NewsRepoScope
@Component(dependencies = NewsAppComponent.class)
public interface NewsRepoComponent {
    void injectNewsRepo(NewsRespository newsRespository);
}
