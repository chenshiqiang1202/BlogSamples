package com.csq.storagetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((TextView)findViewById(R.id.tvInfo)).setText(getStorageInfo());
    }

    private String getStorageInfo(){
        List<StorageVolumeUtil.MyStorageVolume>  list = StorageVolumeUtil.getVolumeList(this);
        if(list != null && !list.isEmpty()){
            StringBuffer sb = new StringBuffer();
            for(StorageVolumeUtil.MyStorageVolume vo : list){
                sb.append(vo.toString() + "\n");
            }
            return sb.toString();
        }
        return "";
    }

}
