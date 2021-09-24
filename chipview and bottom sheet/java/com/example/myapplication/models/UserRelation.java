package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class UserRelation {

    @SerializedName("id")
    private int id;

    @SerializedName("f_name")
    private String f_name;

    @SerializedName("l_name")
    private String l_name;

    @SerializedName("user_knobee_id")
    private String user_knobee_id;

    @SerializedName("profile_pic")
    private String profile_pic;

    @SerializedName("gender")
    private String gender;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRelation(int id, String f_name) {
        this.id = id;
        this.f_name = f_name;
    }

    public UserRelation(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getUser_knobee_id() {
        return user_knobee_id;
    }

    public void setUser_knobee_id(String user_knobee_id) {
        this.user_knobee_id = user_knobee_id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
