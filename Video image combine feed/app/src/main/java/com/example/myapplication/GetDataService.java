package com.example.myapplication;

import com.example.myapplication.models.ParentRelationData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

@GET("chats/getHiveUsersChat.json?level=0")
    Call<ParentRelationData> getAllRelationData();

}
