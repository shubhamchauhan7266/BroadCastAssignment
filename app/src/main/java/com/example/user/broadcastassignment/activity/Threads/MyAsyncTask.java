package com.example.user.broadcastassignment.activity.Threads;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.broadcastassignment.activity.Service.MyService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by user on 15/5/17.
 */

public class MyAsyncTask extends AsyncTask<Object,Object,String>{

    private static final String TAG ="URL Connection" ;
    private static  final String requestmethod="GET";
    private static String Url;
    private static String Data;
    public AsyncResponse asyncResponse;

    private HttpURLConnection connection = null;

    public MyAsyncTask(MyService myService) {
        asyncResponse=(AsyncResponse) myService;
    }

    @Override
    protected String doInBackground(Object... url) {
        Url=(String)url[0];

        Data=formConnection();
        return Data;
    }

    @Override
    protected void onPostExecute(String result) {
        if(asyncResponse!=null){
            asyncResponse.processFinish(result);
        }
    }

    private String formConnection() {

        try {
            connection = (HttpURLConnection) new URL("http://httpbin.org/get?site=code&network=tutsplus").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod(requestmethod);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoInput(true);
        try {
            connection.connect();
            int responseCode = connection.getResponseCode();
            // Get inputStream on connection and read data using Reader
            if (responseCode == HttpURLConnection.HTTP_OK)
                return (consumeResponse(connection.getInputStream()));
            else {
                Log.d("Request Failed", connection.getResponseMessage());
                return null;
            }

        } catch (IOException e) {
            Log.d(TAG, "Connection not formed");
        }
        return null;
    }

    private String consumeResponse(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line;
        StringBuffer sf = new StringBuffer();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sf.append(line).append('\n');
            }
            if (bufferedReader != null) bufferedReader.close();
            if (connection != null) connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sf.toString();
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }
}
