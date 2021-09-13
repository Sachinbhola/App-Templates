package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer.Builder;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<String> mediaList= new ArrayList<String>();
    private Context context;
    SimpleExoPlayer player;
    public String TAG="Crashtest";

    CustomAdapter (Context context,List<String> mediaList){
        this.context=context;
        this.mediaList=mediaList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public View view;
        PlayerView pv;
        ImageView iv;

        public CustomViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            pv=view.findViewById(R.id.exoPlayerPost);
            iv=view.findViewById(R.id.ivImagePost);
        }
    }

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if(viewType==1){
            View view = layoutInflater.inflate(R.layout.image_item,parent,false);
            return new ImageViewHolder(view);
        }
        else{
            View view = layoutInflater.inflate(R.layout.rv_item,parent,false);
            return new VideoViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Log.d(TAG,"call on parent holder");

        if(holder instanceof ImageViewHolder){
            onBindImageViewHolder((ImageViewHolder) holder, position);
        }
        else{
            onBindVideoViewHolder((VideoViewHolder) holder, position);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position,List<Object> payloads) {

        Log.d(TAG,"call on payload holder");

        int viewType = getItemViewType(position);
        if (payloads.isEmpty()) {
            Log.d(TAG,"empty payload case");
            super.onBindViewHolder(holder, position, payloads);
            //return;
        }
        else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("url")) {

                    Log.d(TAG,"payload case in holder");
                    mediaList.add(key);
                    if(holder instanceof ImageViewHolder){
                        onBindImageViewHolder((ImageViewHolder) holder, position);
                    }
                    else{
                        onBindVideoViewHolder((VideoViewHolder) holder, position);
                    }
                }
            }
        }
    }

    public class ImageViewHolder extends CustomViewHolder {

        public View view;
        public ImageView iv;
        public ImageViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv=view.findViewById(R.id.ivImagePost);
        }
    }

    public class VideoViewHolder extends CustomViewHolder {
        public View view;
        public PlayerView pv;
        public VideoViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            pv=view.findViewById(R.id.exoPlayerPost);
        }
    }

    private void onBindImageViewHolder(@NonNull ImageViewHolder holder, int position) {

        Log.d(TAG,"call on image holder");
        showWithGlide(mediaList.get(position),context,holder.itemView.findViewById(R.id.ivImagePost));
    }

    private void onBindVideoViewHolder(@NonNull VideoViewHolder holder, int position) {

        Log.d(TAG,"call on video holder");
        playWithExo(mediaList.get(position),context,holder.itemView.findViewById(R.id.exoPlayerPost));

    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

/*    public void refresh(List<String > mediaList) {
        this.mediaList = mediaList;
        notifyDataSetChanged();
    }
*/
    public void insertData(List<String> insertData){

        MyDiffUtil myDiffUtil = new MyDiffUtil(mediaList,insertData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtil);
        mediaList.addAll(insertData);
        diffResult.dispatchUpdatesTo(this);
    }


    public void updateData(List<String> newData){
        Log.d(TAG,"function called");
        Log.d(TAG,"old list-"+mediaList.size()+"new list"+newData.size());
        MyDiffUtil diffUtilCallback = new MyDiffUtil(mediaList,newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        mediaList.clear();
        mediaList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
        Log.d(TAG,"old list-"+mediaList.size()+" ,new list"+newData.size());
        Log.d(TAG,"function ended");
    }

    @Override
    public int getItemViewType(int position) {
        String url=mediaList.get(position);
        String mimeType= URLConnection.guessContentTypeFromName(url);
        Boolean isImage=url!=null && mimeType.startsWith("image");
        if(isImage) return 1;
        else return 0;
    }

    public void playWithExo(String CONTENT_URL, Context context, PlayerView playerView) {
        player= new Builder(context).build();
        String userAgent = Util.getUserAgent(context, "vid");
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context, userAgent);
        MediaItem mediaItem = MediaItem.fromUri(CONTENT_URL);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
/*      MediaSource mediaSource = new ProgressiveMediaSource.Factory(defaultmediaListFactory).createMediaSource(mediaItem);
        player.setMediaSource(mediaSource,true);
        player.setPlayWhenReady(true);*/
        playerView.setPlayer(player);


    }
    public void pausePlayer(SimpleExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

    public void playPlayer(SimpleExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    public void showWithGlide(String CONTENT_URL, Context context, ImageView imageView){

        Glide.with(context).load(CONTENT_URL).placeholder(R.mipmap.ic_launcher).into(imageView);
    }


}

//    @Override
//    public void onViewAttachedToWindow(@NonNull CustomViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        //Toast.makeText(context,"play",Toast.LENGTH_SHORT).show();
//        playPlayer(player);
//    }
//
//    @Override
//    public void onViewDetachedFromWindow(@NonNull CustomViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        //Toast.makeText(context,"pause",Toast.LENGTH_LONG).show();
//        pausePlayer(player);
//    }

//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        playWithExo(mediaList.get(position),context,holder.itemView.findViewById(R.id.exoPlayerPost));
//    }
