package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.models.ParentRelationData;
import com.example.myapplication.models.RelationData;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    PlayerView pvMain;
    List<String> mediaContent= new ArrayList<String>();
    List<String> updatedContent= new ArrayList<String>();
    CustomAdapter customAdapter;
    Context context= MainActivity.this;
    RecyclerView recyclerView;
    Button btn;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();


        mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
        //mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/07/Sample-JPEG-Image-File-Download.jpg");
        mediaContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");

        updatedContent.addAll(mediaContent);

        recyclerView = findViewById(R.id.rvPost_Media);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        generateList(mediaContent);
        btn=findViewById(R.id.btnAddToList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.getRecycledViewPool().clear();
                updatedContent.add("https://www.learningcontainer.com/wp-content/uploads/2020/07/Sample-JPEG-Image-File-Download.jpg");
                //customAdapter.insertData(mediaContent);
                customAdapter.updateData(updatedContent);

            }
        });

        swipeRefreshLayout=findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //customAdapter.updateData(mediaContent);
                generateList(mediaContent);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    public void generateList(List<String> mediaContent){
        customAdapter=new CustomAdapter(context,mediaContent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);


    }

}