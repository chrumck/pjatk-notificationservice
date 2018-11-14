package com.chrumck.pjatk.notificationservice;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NotificationActionReceiver", "received intent: " + intent.getAction());

        context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        NotificationManager notificationManager = (NotificationManager) context
                .getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(intent.getIntExtra(NotificationService.NOTIFICATION_ID, 0));
    }
}
