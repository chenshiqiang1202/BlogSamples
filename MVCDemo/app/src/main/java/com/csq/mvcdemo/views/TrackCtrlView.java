/**
 * description : 轨迹控制View相关逻辑
 * Created by csq E-mail:csqwyyx@163.com
 * 15-4-26
 */
package com.csq.mvcdemo.views;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.csq.mvcdemo.R;
import com.csq.mvcdemo.models.TrackRecordInfo;
import com.csq.mvcdemo.models.TrackRecordStatus;

public class TrackCtrlView implements View.OnClickListener{

    // ------------------------ Constants ------------------------


    // ------------------------- Fields --------------------------

    private ImageView btnStartTrack, btnStopTrack, btnPauseTrack;
    private TrackCtrlViewListener listener;
    private TrackRecordInfo trackRecordInfo;


    // ----------------------- Constructors ----------------------

    public TrackCtrlView(Activity activity, TrackCtrlViewListener listener){
        this.listener = listener;
        btnStartTrack = (ImageView) activity.findViewById(R.id.btnStartTrack);
        btnStopTrack = (ImageView) activity.findViewById(R.id.btnStopTrack);
        btnPauseTrack = (ImageView) activity.findViewById(R.id.btnPauseTrack);
        btnStartTrack.setOnClickListener(this);
        btnStopTrack.setOnClickListener(this);
        btnPauseTrack.setOnClickListener(this);
        btnPauseTrack.setOnClickListener(this);
    }


    // -------- Methods for/from SuperClass/Interfaces -----------

    /**
     * 将用户请求通知Controller
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStartTrack:
                if(listener != null){
                    listener.trackStatusRequest(TrackRecordStatus.Recording);
                }
                break;

            case R.id.btnStopTrack:
                if(listener != null){
                    listener.trackStatusRequest(TrackRecordStatus.Stoped);
                }
                break;

            case R.id.btnPauseTrack:
                if(listener != null){
                    if(trackRecordInfo.status == TrackRecordStatus.Paused){
                        listener.trackStatusRequest(TrackRecordStatus.Recording);
                    }else{
                        listener.trackStatusRequest(TrackRecordStatus.Paused);
                    }
                }
                break;

            default:

                break;
        }
    }

    // --------------------- Methods public ----------------------


    // --------------------- Methods private ---------------------

    private void refreshView(){
        TrackRecordStatus trackStatus = trackRecordInfo == null ?
                TrackRecordStatus.Stoped : trackRecordInfo.status;
        if (trackStatus == TrackRecordStatus.Recording) {
            btnStartTrack.setVisibility(View.GONE);
            btnPauseTrack.setVisibility(View.VISIBLE);
            btnStopTrack.setVisibility(View.VISIBLE);
            btnPauseTrack.setImageResource(R.drawable.btn_track_ctrl_pause);

        } else if (trackStatus == TrackRecordStatus.Paused) {
            btnStartTrack.setVisibility(View.GONE);
            btnPauseTrack.setVisibility(View.VISIBLE);
            btnStopTrack.setVisibility(View.VISIBLE);
            btnPauseTrack.setImageResource(R.drawable.btn_track_ctrl_resume);

        } else {
            // TrackRecordStatus.Stoped
            btnStartTrack.setVisibility(View.VISIBLE);
            btnPauseTrack.setVisibility(View.GONE);
            btnStopTrack.setVisibility(View.GONE);
        }
    }

    // --------------------- Getter & Setter -----------------

    public void setTrackRecordInfo(@Nullable TrackRecordInfo trackRecordInfo) {
        this.trackRecordInfo = trackRecordInfo;
        refreshView();
    }


    // --------------- Inner and Anonymous Classes ---------------

    public interface TrackCtrlViewListener{
        /**
         * 用户点击按钮
         */
        public void trackStatusRequest(@Nullable TrackRecordStatus newStatus);
    }

    // --------------------- logical fragments -----------------

}
