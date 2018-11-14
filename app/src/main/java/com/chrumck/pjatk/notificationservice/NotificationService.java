package com.chrumck.pjatk.notificationservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.util.Log;

import java.util.Random;

public class NotificationService extends Service {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    @Override
    public void onCreate() {
        Log.i("NotificationService", "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationService", "Service started");

        int notificationId = new Random().nextInt();

        Intent editIntent = new Intent(getString(R.string.myshoppinglist_action_product_edit));
        editIntent.putExtra("id", intent.getIntExtra("id", 0));
        editIntent.putExtra(NOTIFICATION_ID, notificationId);
        PendingIntent editPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent listIntent = new Intent(getString(R.string.myshoppinglist_action_product_list));
        listIntent.putExtra(NOTIFICATION_ID, notificationId);
        PendingIntent listPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, listIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Shopping list item created: " + intent.getStringExtra("name"))
                .setContentText("Quantity: " + Integer.toString(intent.getIntExtra("quantity", 0)))
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .addAction(new Action(R.drawable.ic_edit_black_24dp, "Edit Item", editPendingIntent))
                .addAction(new Action(R.drawable.ic_list_black_24dp, "List All", listPendingIntent))
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("NotificationService", "Service bound");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("NotificationService", "Service destroyed");
    }


}
