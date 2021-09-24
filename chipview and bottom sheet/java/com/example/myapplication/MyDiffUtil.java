package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.load.model.Model;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

public class MyDiffUtil extends DiffUtil.Callback {

    public String TAG="AdapterTest";
    List<String > oldList;
    List<String > newList;

    public MyDiffUtil(List<String > oldList,List<String > newList){
        Log.d(TAG,"diffutil constructor call");
        Log.d(TAG,"old list-"+oldList.size()+"new list" +newList.size());
        this.oldList=oldList;
        this.newList=newList ;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition==newItemPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition)==newList.get(newItemPosition);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        String newModel=newList.get(newItemPosition);
        String oldModel=oldList.get(oldItemPosition);

        Bundle difference = new Bundle();
        Log.d(TAG,"data changed-payload add request by diffutil");
        if(difference.size()==0){
            return null;
        }
        if(newModel!=oldModel){
            difference.putString("name", newModel);
        }

        return difference;
        //return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
