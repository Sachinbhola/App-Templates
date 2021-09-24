package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.RelationData;
import com.example.myapplication.models.Relations;
import com.example.myapplication.models.RelationModel;
import com.example.myapplication.models.ReportModel;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PlayerView pvMain;
    List<String> mediaContent= new ArrayList<String>();
    List<String> updatedContent= new ArrayList<String>();
    public List<RelationData> relationNameList= new ArrayList<RelationData>();
    List<RelationData> updatedRelationNameList=new ArrayList<RelationData>();
    static List<String> reportList = new ArrayList<String>();
    CustomAdapter customAdapter;
    RelationsAdapter relationsAdapter;
    Context context= MainActivity.this;
    RecyclerView recyclerView;
    Button btn;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tvName;
    TextView test;
    RecyclerView rv2;

    public String TAG="Crashtest";

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =findViewById(R.id.btnAddToList);
        recyclerView = findViewById(R.id.rvPost_Media);
        tvName=findViewById(R.id.tvName);
        //getData(relationNameList);

        init();

        ChipGroup chipGroup=findViewById(R.id.chip);
        Chip chip1= new Chip(context);
        chip1.setText("cbdgbvfdgvldbkdn");
        chip1.setCheckable(true);

        Chip chip2= new Chip(context);
        chip2.setText("cbdgbvfdgvldbkdn");
        chip2.setCheckable(true);

        chipGroup.addView(chip1);
        chipGroup.addView(chip2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyApplication.level += 1;
//                getData(relationNameList);
//                relationsAdapter.updateData(relationNameList);


                BottomSheetFragment bottomSheet = new BottomSheetFragment(context);
                bottomSheet.show(getSupportFragmentManager(), "BottomSheet");

                //postData();

            }

        });

        swipeRefreshLayout=findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //customAdapter.updateData(mediaContent);
                generateList(relationNameList);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                MyApplication.level += 1;
//                Log.d(TAG,"level changed to"+MyApplication.level);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //generateList();
//                    }
//                }, 1000);
//            }
//        });
        //generateList();

    }
    public static void init(){
        reportList.add("a");
        reportList.add("b");
        reportList.add("c");

        MyGlobal.yesClicked.add(false);
        MyGlobal.yesClicked.add(false);
        MyGlobal.yesClicked.add(false);


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

    private void getData(List<RelationData> relationNameList) {

        //GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        GetDataService service = RetrofitInstance.getClient().create(GetDataService.class);
        RelationModel model = new RelationModel("","","$2y$10$qsuCVygZipDUIktg1Kd1ieRdzKPma6Lla9gUlpPQTtH2SR.feuAGa",
                "3344","3344");

        Call<Relations> call = service.getAllRelationData(MyGlobal.level,model);

        call.enqueue(new Callback<Relations>() {
            @Override
            public void onResponse(@NonNull Call<Relations> call, @NonNull Response<Relations> response) {

                if(response.body()!=null) {
                    relationNameList.addAll(response.body().getData());
                    Log.d(TAG, "api list size = " + relationNameList.size());
                    generateList(relationNameList );

                }
                else{
                    Toast.makeText(context,"null value recieved",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure( Call<Relations> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }


    public void generateList(List<RelationData> relationNameList){
        Log.d(TAG,"inside fun call list size = "+relationNameList.size());
        relationsAdapter=new RelationsAdapter(relationNameList,context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(relationsAdapter);

    }



}
//
//
//        mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
//        //mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/07/Sample-JPEG-Image-File-Download.jpg");
//        mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
//
//        updatedContent.addAll(mediaContent);


//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);


//                    tvFollowersCount.setText(response.body().getProfileData().getUserDetails().getFollowerCount());
//                    tvFollowingCount.setText(response.body().getProfileData().getUserDetails().getFollowingCount());
//                    tvStatus.setText(response.body().getProfileData().getUserDetails().getStatus());


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                recyclerView.getRecycledViewPool().clear();
//                updatedContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/07/Sample-JPEG-Image-File-Download.jpg");
//                //customAdapter.insertData(mediaContent);
//                customAdapter.updateData(updatedContent);
//
//            }
//        });
//
