package com.csq.multiplelist.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.csq.multiplelist.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnMethod1:
                Method1Activtiy.launchActivity(this);
                break;

            case R.id.btnMethod2:
                Method2Activtiy.launchActivity(this);
                break;

            case R.id.btnMethod3:
                Method3Activtiy.launchActivity(this);
                break;

            default:

                break;
        }
    }


}
