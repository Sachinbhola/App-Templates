package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class UserRelation {

    @SerializedName("id")
    private int id;

    @SerializedName("f_name")
    private int f_name;

    @SerializedName("l_name")
    private String l_name;

    @SerializedName("user_knobee_id")
    private String user_knobee_id;

    @SerializedName("profile_pic")
    private String profile_pic;

    @SerializedName("gender")
    private String gender;

    @SerializedName("is_verified")
    private Boolean is_verified;

    @SerializedName("country")
    private String country;

    @SerializedName("state")
    private String state;

    @SerializedName("city")
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getF_name() {
        return f_name;
    }

    public void setF_name(int f_name) {
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

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
