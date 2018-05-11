package com.example.welcome.task.repository.model;

import com.google.gson.annotations.SerializedName;

public class NewsFeedModel {

    public Channel channel;

    @SerializedName("@version")
    public String version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
