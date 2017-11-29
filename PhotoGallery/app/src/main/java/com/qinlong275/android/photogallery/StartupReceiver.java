package com.qinlong275.android.photogallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 秦龙 on 2017/10/5.
 */

public class StartupReceiver extends BroadcastReceiver{

    private static final String TAG="StartupReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Received broadcast intent: "+intent.getAction());

        boolean isOn=QueryPreferences.isAlarmOn(context);
        PollService.setServiceAlarm(context,isOn);
    }
}
