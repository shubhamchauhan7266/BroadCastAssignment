package com.example.user.broadcastassignment.activity.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.user.broadcastassignment.activity.Broadcast.MyBroadcast;
import com.example.user.broadcastassignment.activity.Threads.MyAsyncTask;

/**
 * Created by user on 15/5/17.
 */

public class MyService extends Service implements MyAsyncTask.AsyncResponse{
    private final String url="http://httpbin.org/get?site=code&network=tutsplus";
    private String stringData;
    private Intent intent;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(isOnline())
        {
            new MyAsyncTask(MyService.this).execute(url);
            stopSelf();
        }else
        {
            Toast.makeText(MyService.this,"No network available",Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
    }

    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void processFinish(String output) {
        stringData=output;
        this.intent=new Intent(this, MyBroadcast.class);
        intent.setAction("com.example.user.broadcastassignment.activity.Broadcast");
        intent.putExtra("Data", stringData);
        sendBroadcast(intent);
    }
}
