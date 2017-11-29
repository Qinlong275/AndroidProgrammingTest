package com.qinlong275.android.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by 秦龙 on 2017/10/5.
 */

public class NotificationReceiver extends BroadcastReceiver{
    private static final String TAG="NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"received result: "+getResultCode());
        if (getResultCode()!= Activity.RESULT_OK){
            return;
        }

        int requestCode= intent.getIntExtra(PollService.REQUEST_CODE,0);
        Notification notification=(Notification) intent.getParcelableExtra(PollService.NOTIFICATION);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
        notificationManager.notify(requestCode,notification);
    }
}
