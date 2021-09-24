package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class RelationModel {

    @SerializedName("relation")
    private String relation;

    @SerializedName("relation_name")
    private String relation_name;

    @SerializedName("token")
    private String token;

    @SerializedName("user_hive_id")
    private String user_hive_id;

    @SerializedName("user_id")
    private String user_id;

    public RelationModel(String relation, String relation_name, String token, String user_hive_id, String user_id) {
        this.relation = relation;
        this.relation_name = relation_name;
        this.token = token;
        this.user_hive_id = user_hive_id;
        this.user_id = user_id;
    }
    public String getUser_hive_id() {
        return user_hive_id;
    }

    public void setUser_hive_id(String user_hive_id) {
        this.user_hive_id = user_hive_id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation_name() {
        return relation_name;
    }

    public void setRelation_name(String relation_name) {
        this.relation_name = relation_name;
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

//    public RelationModel(String relation,String relation_name,String token,String user_hive_id){
//        this.relation=relation;
//        this.relation_name=relation_name;
//        this.token=token;
//        this.user_hive_id=user_hive_id;
//    }
}
