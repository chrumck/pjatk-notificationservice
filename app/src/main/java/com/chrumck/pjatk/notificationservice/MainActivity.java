package com.chrumck.pjatk.notificationservice;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ProductCreatedReceiver productCreatedReceiver = new ProductCreatedReceiver();
    private NotificationActionReceiver notificationActionReceiver = new NotificationActionReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        IntentFilter productCreatedFilter = new IntentFilter(
                getResources().getString(R.string.myshoppinglist_action_product_created));
        String permission = getResources().getString(R.string.app_permission_broadcast_rec);
        registerReceiver(productCreatedReceiver, productCreatedFilter, permission, null);
        Log.i("MainActivity", "productCreatedReceiver registered");

        IntentFilter hideFilter = new IntentFilter();
        hideFilter.addAction(getResources().getString(R.string.myshoppinglist_action_product_list));
        hideFilter.addAction(getResources().getString(R.string.myshoppinglist_action_product_edit));
        registerReceiver(notificationActionReceiver, hideFilter);
        Log.i("MainActivity", "notificationActionReceiver registered");
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(productCreatedReceiver);
        Log.i("MainActivity", "productCreatedReceiver un-registered");

        unregisterReceiver(notificationActionReceiver);
        Log.i("MainActivity", "notificationActionReceiver un-registered");

        super.onDestroy();
    }
}
