package com.chrumck.pjatk.notificationservice;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ProductCreatedReceiver productCreatedReceiver = new ProductCreatedReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        IntentFilter filter = new IntentFilter(getResources().getString(R.string.myshoppinglist_action_product_created));
        String permission = getResources().getString(R.string.app_permission_broadcast_rec);
        registerReceiver(productCreatedReceiver, filter, permission, null);
        Log.i("NotificationService", "productCreatedReceiver registered");
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(productCreatedReceiver);
        Log.i("NotificationService", "productCreatedReceiver un-registered");

        super.onDestroy();
    }
}
