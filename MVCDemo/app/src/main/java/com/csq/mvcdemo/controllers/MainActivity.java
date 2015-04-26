package com.csq.mvcdemo.controllers;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.csq.mvcdemo.R;
import com.csq.mvcdemo.events.EventTrackRecordInfoChanged;
import com.csq.mvcdemo.models.TrackRecordInfo;
import com.csq.mvcdemo.models.TrackRecordStatus;
import com.csq.mvcdemo.views.TrackCtrlView;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity implements TrackCtrlView.TrackCtrlViewListener{

    private TrackCtrlView trackCtrlView;
    private TrackRecordInfo trackRecordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackCtrlView = new TrackCtrlView(this, this);

        EventBus.getDefault().register(this);

        trackRecordInfo = TrackRecordInfo.loadTrackRecordInfo(this);
        trackCtrlView.setTrackRecordInfo(trackRecordInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }


    @Override
    public void trackStatusRequest(@Nullable TrackRecordStatus newStatus) {
        if(newStatus == TrackRecordStatus.Recording){
            int trackId = 0;  //在数据库创建一条轨迹，并获取到数据库id
            trackRecordInfo = new TrackRecordInfo(trackId, TrackRecordStatus.Recording);

        }else if (newStatus == TrackRecordStatus.Paused) {
            if(trackRecordInfo != null){
                trackRecordInfo.status = newStatus;
            }

        } else {
            trackRecordInfo = null;
        }
        TrackRecordInfo.changeTrackRecordInfo(this, trackRecordInfo);
    }

    public void onEventMainThread(EventTrackRecordInfoChanged event){
        trackRecordInfo = event.info;
        trackCtrlView.setTrackRecordInfo(trackRecordInfo);
    }

}
