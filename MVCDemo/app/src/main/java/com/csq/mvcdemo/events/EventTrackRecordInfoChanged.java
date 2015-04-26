/**
 * description : 轨迹记录信息改变事件
 * Created by csq E-mail:csqwyyx@163.com
 * 15-4-26
 */
package com.csq.mvcdemo.events;

import android.support.annotation.Nullable;

import com.csq.mvcdemo.models.TrackRecordInfo;

public class EventTrackRecordInfoChanged {

    // ------------------------ Constants ------------------------


    // ------------------------- Fields --------------------------

    @Nullable
    public final TrackRecordInfo info;

    // ----------------------- Constructors ----------------------

    public EventTrackRecordInfoChanged(TrackRecordInfo info) {
        this.info = info;
    }


    // -------- Methods for/from SuperClass/Interfaces -----------


    // --------------------- Methods public ----------------------


    // --------------------- Methods private ---------------------


    // --------------------- Getter & Setter -----------------


    // --------------- Inner and Anonymous Classes ---------------


    // --------------------- logical fragments -----------------

}
