package com.example.user.broadcastassignment.activity.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.user.broadcastassignment.activity.activity.MainActivity;

/**
 * Created by user on 15/5/17.
 */

public class MyBroadcast extends BroadcastReceiver{
    private   Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("com.example.user.broadcastassignment.activity.Broadcast")){
            String stringData = intent.getExtras().getString("Data");
            this.intent = new Intent(context,MainActivity.class);
            this.intent.putExtra("Data", stringData);
            this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(this.intent);

        }
    }
}
