package com.example.myapplication;

import com.example.myapplication.models.RelationModel;
import com.example.myapplication.models.Relations;
import com.example.myapplication.models.ReportModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

    static int lvl=  MyGlobal.level;

    @Headers("Content-Type: application/json")
    @POST("Chats/getHiveUsersChat.json")
    Call<Relations> getAllRelationData(@Query("level") int lvl,@Body RelationModel relationModel);

    @Headers("Content-Type: application/json")
    @POST("posts/postReport.json")
        Call<ReportModel> getReport(@Body ReportModel reportModel);

}
