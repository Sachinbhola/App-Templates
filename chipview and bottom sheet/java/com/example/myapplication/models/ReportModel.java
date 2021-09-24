package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class ReportModel {

    @SerializedName("description")
    public String description;

    @SerializedName("post_id")
    private String post_id;

    @SerializedName("title")
    private String title;

    public ReportModel(String description, String post_id, String title, String token, String user_id) {
        this.description = description;
        this.post_id = post_id;
        this.title = title;
        this.token = token;
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    @SerializedName("token")
    private String token;

    @SerializedName("user_id")
    private String user_id;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }




}
