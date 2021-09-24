package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Relations {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<RelationData> data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RelationData> getData() {
        return data;
    }

    public void setData(List<RelationData> data) {
        this.data = data;
    }
}
