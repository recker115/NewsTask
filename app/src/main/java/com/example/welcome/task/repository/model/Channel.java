package com.example.welcome.task.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Channel {

    @SerializedName("item")
    List<EachItem> items;

    public List<EachItem> getItems() {
        return items;
    }

    public void setItems(List<EachItem> items) {
        this.items = items;
    }
}
