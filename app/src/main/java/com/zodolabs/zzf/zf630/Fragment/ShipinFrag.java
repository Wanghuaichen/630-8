package com.zodolabs.zzf.zf630.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.util.ExternalFileHelper;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.Utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzf on 17-12-6.
 */

public class ShipinFrag extends UltimateFragment {
    RecyclerView lv;
    VideoView videoView;

    VideoAdapter videoAdapter;
    List<File> videolist;

    @Override
    protected int setContentView() {
        return R.layout.frag_shipin;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv_video);
        videoView = findViewById(R.id.videoview);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        videolist = new ArrayList<>();
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setAdapter(videoAdapter = new VideoAdapter(getActivity(), videolist, R.layout.video_item));
        Log.d(TAG, "" + ExternalFileHelper.getInstance().isFileExists(Utils.getVideopath()));
        if (ExternalFileHelper.getInstance().isFileExists(Utils.getVideopath())) {
            File[] files = new File(Utils.getVideopath()).listFiles();
            for (File file : files) {
                videolist.add(file);
            }
            videoAdapter.notifyDataSetChanged();
        }else{
            new File(Utils.getVideopath()).mkdirs();
            toast("请先把视频文件存入手机目录：/zodolabs/video/");
        }
        videoAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<File>() {
            @Override
            public void onRecycleItemClickListener(File file, View view, int position, long id, int type) {
                videoView.setVideoPath(file.getAbsolutePath());
                videoView.setMediaController(new MediaController(getActivity()));
                videoView.start();
            }
        });
    }

    private class VideoAdapter extends UltimateRecycleAdapter<File> {
        public VideoAdapter(Context context, List<File> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(File s, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.tv_video, s.getName());
        }
    }
}
