package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.myapplication.models.RelationData;
import com.example.myapplication.models.Relations;

import java.util.List;

public class RelationDiffUtil extends DiffUtil.Callback {

public String TAG="AdapterTest";
        List<RelationData> oldList;
        List<RelationData > newList;

public RelationDiffUtil(List<RelationData > oldList,List<RelationData > newList){
        Log.d(TAG,"diffutil constructor call");
        Log.d(TAG,"old list-"+oldList.size()+"new list"+newList.size());
        this.oldList=oldList;
        this.newList=newList;
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
        return oldList.get(oldItemPosition).getRelation_user().getId()==newList.get(newItemPosition).getRelation_user().getId();
        }

@Override
public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return (oldList.get(oldItemPosition).getRelation_user().getF_name().equals(newList.get(newItemPosition).getRelation_user().getF_name()) &&
         oldList.get(oldItemPosition).getRelation_user().getL_name().equals(newList.get(newItemPosition).getRelation_user().getL_name()));
        }

@Nullable
@Override
public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        RelationData newModel=newList.get(newItemPosition);
        RelationData oldModel=oldList.get(oldItemPosition);

        Bundle difference = new Bundle();
        Log.d(TAG,"payload adding request call internal");
        if(difference.size()==0){
        return null;
        }
        if(newModel!=oldModel){
        difference.putString("url", newModel.getRelation_user().getF_name());
        }

        return difference;
        }
}


