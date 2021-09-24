package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.RelationData;
import com.example.myapplication.models.RelationModel;
import com.example.myapplication.models.Relations;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RelationsAdapter extends RecyclerView.Adapter<RelationsAdapter.CustomViewHolder> {

    private List<RelationData> relationList;
    private Context context;
    public String TAG="AdapterTest";

    RelationsAdapter(List<RelationData> relationList, Context context){
        this.context=context;
        this.relationList=relationList;
        Log.d(TAG,"constructor");
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public View view;
        TextView tvName;
        TextView tvRel;

        public CustomViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvName=view.findViewById(R.id.tvName);
            tvRel=view.findViewById(R.id.tvRelated);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG,"call on create");
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.rv_item,parent,false);
            return new RelationsAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.d(TAG,"call on normal bind");
        holder.tvName.setText(relationList.get(position).getRelation_user().getF_name());
        holder.tvRel.setText(relationList.get(position).getRelationName());

    }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position,List<Object> payloads) {
            Log.d(TAG,"call on payload on bind");
        int viewType = getItemViewType(position);
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            //return;
        }
        else {
            Log.d(TAG,"data change detected");
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("name")) {
                    relationList.get(position).getRelation_user().setF_name(key);
                    holder.tvName.setText(relationList.get(position).getRelation_user().getF_name());
                    holder.tvRel.setText(relationList.get(position).getRelationName());
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        Log.d(TAG,"getitemcount() of list inside adapter = "+ relationList.size());
        return relationList.size();
    }

    public void updateData(List<RelationData> newData){
        Log.d(TAG,"function called");
        Log.d(TAG,"old list-"+relationList.size()+"new list"+newData.size());
        RelationDiffUtil diffUtilCallback = new RelationDiffUtil(relationList,newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        relationList.clear();
        relationList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
        Log.d(TAG,"old list-"+relationList.size()+" ,new list"+newData.size());
        Log.d(TAG,"function ended");
    }
}
