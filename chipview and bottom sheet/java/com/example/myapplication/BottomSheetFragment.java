package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.models.RelationData;
import com.example.myapplication.models.ReportModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragment extends BottomSheetDialogFragment{
    Context context;
    static String TAG="bottomsheet";

    List<String> reportedList = new ArrayList<String>();

    public BottomSheetFragment(Context context) {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.bottom_sheet,container,false);
        RecyclerView rv = v.findViewById(R.id.rvReportType);
        Button btn= v.findViewById(R.id.btnSentReport);
        BottomSheetAdapter bottomSheetAdapter=new BottomSheetAdapter(context);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
//        rv.setLayoutManager(layoutManager);
//        rv.setAdapter(bottomSheetAdapter);

        reportedList.add("Nudity");
        reportedList.add("Violence");
        reportedList.add("Harassment");
        reportedList.add("Suicide or self-injury");
        reportedList.add("Hate speech");
        reportedList.add("Unauthorised sales");
        reportedList.add("Terrorism");
        reportedList.add("Spam");
        reportedList.add("False information");
        reportedList.add("Something else");



        ChipGroup chipGroup=v.findViewById(R.id.chipsy);

//        for (String s:
//             reportedList) {
//            Chip chip = new Chip(context);
//            chip.setText(s);
//            chipGroup.addView(chip);
//        }
//
        Chip chip1= new Chip(context);
        Chip chip2= new Chip(context);
        Chip chip3= new Chip(context);
        Chip chip4= new Chip(context);
        Chip chip5= new Chip(context);
        Chip chip6= new Chip(context);
        Chip chip7= new Chip(context);
        Chip chip8= new Chip(context);
        Chip chip9= new Chip(context);
        Chip chip10= new Chip(context);

        chip1.setText(reportedList.get(0));
        chip2.setText(reportedList.get(1));
        chip3.setText(reportedList.get(2));
        chip4.setText(reportedList.get(3));
        chip5.setText(reportedList.get(4));
        chip6.setText(reportedList.get(5));
        chip7.setText(reportedList.get(6));
        chip8.setText(reportedList.get(7));
        chip9.setText(reportedList.get(8));
        chip10.setText(reportedList.get(9));

        chip1.setCheckable(true);
        chip2.setCheckable(true);
        chip3.setCheckable(true);
        chip4.setCheckable(true);
        chip5.setCheckable(true);
        chip6.setCheckable(true);
        chip7.setCheckable(true);
        chip8.setCheckable(true);
        chip9.setCheckable(true);
        chip10.setCheckable(true);

        chipGroup.addView(chip1);
        chipGroup.addView(chip2);
        chipGroup.addView(chip3);
        chipGroup.addView(chip4);
        chipGroup.addView(chip5);
        chipGroup.addView(chip6);
        chipGroup.addView(chip7);
        chipGroup.addView(chip8);
        chipGroup.addView(chip9);
        chipGroup.addView(chip10);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chip1.isChecked()){
                    Log.d(TAG, "onClick: chip1 selected");
                    MyGlobal.reportContentList.add(reportedList.get(0));
                }
                if(chip2.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(1));
                }
                if(chip3.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(2));
                }
                if(chip4.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(3));
                }
                if(chip5.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(4));
                }
                if(chip6.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(5));
                }
                if(chip7.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(6));
                }
                if(chip8.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(7));
                }
                if(chip9.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(8));
                }
                if(chip10.isChecked()){
                    MyGlobal.reportContentList.add(reportedList.get(9));
                }

                String temp=new String();
                for(String s: MyGlobal.reportContentList){
                    if(temp.isEmpty()){
                        temp=s;
                    }
                    else {
                        temp = temp + " or " + s;
                    }
                }

                MyGlobal.reports = temp;
                Log.d(TAG, "onClick: "+ MyGlobal.reports);
                Toast.makeText(context,MyGlobal.reports,Toast.LENGTH_LONG).show();

                postData();

                btn.setClickable(false);

                dismiss();
            }

            private void postData() {
                GetDataService service = RetrofitInstance.getClient().create(GetDataService.class);
                ReportModel reportModel = new ReportModel("","975",MyGlobal.reports,"$2y$10$GFUpdze3G/Vu3W1IatT3cOSmVFdJ.3lZGxh1i2HnkZw7ru/gtctnW","3344");
                Call<ReportModel> call= service.getReport(reportModel);
                call.enqueue(new Callback<ReportModel>() {
                    @Override
                    public void onResponse(Call<ReportModel> call, Response<ReportModel> response) {
                        Toast.makeText(context, "Reported", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: success");
                    }

                    @Override
                    public void onFailure(Call<ReportModel> call, Throwable t) {
                        Toast.makeText(context, "failed to report", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: failure");
                    }
                });
            }
        });

        return v;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }
}
 /*

   this is extra code which was replaced by adapter in recycler view




 class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
}




     rv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Log.d(TAG, "onItemClick:"+ MyGlobal.yesClicked.get(position));
                        if(MyGlobal.yesClicked.get(position)){
                            view.setBackgroundColor(R.drawable.white);
                            MyGlobal.yesClicked.set(position,false);
                            Log.d(TAG, "onClick: white");
                        }
                        else{
                            view.setBackgroundColor(R.drawable.blue);
                            MyGlobal.yesClicked.set(position,true);
                            Log.d(TAG, "onClick: blue");
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
  );
*/