/**
 * description : 轨迹记录信息
 * Created by csq E-mail:csqwyyx@163.com
 * 15-4-26
 */
package com.csq.mvcdemo.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.csq.mvcdemo.events.EventTrackRecordInfoChanged;
import com.csq.mvcdemo.utils.SpUtil;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

public class TrackRecordInfo {

    // ------------------------ Constants ------------------------


    // ------------------------- Fields --------------------------

    private static final Gson gson = new Gson();

    /**
     * 应该是保存轨迹数据库id，此demo中数据库操作不实现，暂时trackId一直为0
     */
    public int trackId;

    public TrackRecordStatus status;

    // ----------------------- Constructors ----------------------

    public TrackRecordInfo(int trackId, TrackRecordStatus status) {
        this.trackId = trackId;
        this.status = status;
    }


    // -------- Methods for/from SuperClass/Interfaces -----------


    // --------------------- Methods public ----------------------

    @NonNull
    public static TrackRecordInfo loadTrackRecordInfo(@NonNull Context context){
        String pref = SpUtil.getString(context, SpUtil.KEY_TRACK_RECORD_INFO, "");
        if(!TextUtils.isEmpty(pref)){
            return gson.fromJson(pref, TrackRecordInfo.class);
        }
        return null;
    }

    public static void changeTrackRecordInfo(@NonNull Context context, @Nullable TrackRecordInfo info){
        SpUtil.saveString(context,
                SpUtil.KEY_TRACK_RECORD_INFO,
                info == null ? "" : gson.toJson(info));

        //model通过消息总线，通知View刷新
        EventBus.getDefault().post(new EventTrackRecordInfoChanged(info));
    }

    // --------------------- Methods private ---------------------


    // --------------------- Getter & Setter -----------------


    // --------------- Inner and Anonymous Classes ---------------


    // --------------------- logical fragments -----------------

}
