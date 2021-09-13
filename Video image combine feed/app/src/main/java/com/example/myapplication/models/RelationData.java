package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelationData {

    @SerializedName("relationName")
    private String relationName;

    @SerializedName("relation_user")
    private List<UserRelation> relation_user;

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public List<UserRelation> getRelation_user() {
        return relation_user;
    }

    public void setRelation_user(List<UserRelation> relation_user) {
        this.relation_user = relation_user;
    }
}
