package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.CustomViewHolder> {

    Context context;
    List<String> reportType;
    static String TAG="bottomsheet";


    public BottomSheetAdapter(Context context,List<String> reportType) {

        this.reportType=reportType;
    }

    public BottomSheetAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.report_item,parent,false);
        return new BottomSheetAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.btn.setText(reportType.get(position));

        holder.btn.setOnClickListener(view -> {
            Log.d(TAG, "onItemClick:"+ MyGlobal.yesClicked.get(holder.getAbsoluteAdapterPosition()));

            if(MyGlobal.yesClicked.get(holder.getAbsoluteAdapterPosition())){
                holder.btn.setBackgroundColor(0);
                MyGlobal.yesClicked.set(holder.getAbsoluteAdapterPosition(),false);
                Log.d(TAG, "onClick: white");
                MyGlobal.reportContentList.remove((String) holder.btn.getText());
                // holder.btn.getText()
            }
            else{
                holder.btn.setBackgroundColor(R.drawable.blue);
                MyGlobal.yesClicked.set(holder.getAbsoluteAdapterPosition(),true);

                MyGlobal.reportContentList.add((String) holder.btn.getText());
                Log.d(TAG, "onClick: blue");
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: right adapter");
        return reportType.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        View view;
        Button btn;

        public CustomViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            btn=view.findViewById(R.id.button);

        }
    }
}
