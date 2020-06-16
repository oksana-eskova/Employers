package com.oieskova.employers.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {
    public static final String URL_STRING="http://gitlab.65apps.com/65gb/static/raw/master/testTask.json";

    public static URL buildURL(){
        Uri uri=Uri.parse(URL_STRING).buildUpon().build();
        URL result=null;
        try {
            result=new URL(uri.toString()) ;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static  class JSONLoadTask extends AsyncTask<java.net.URL,Void, JSONObject>{
        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject result=null;
            if(urls==null||urls.length==0){
                return result;
            }
            HttpURLConnection connection=null;
            try {
                connection=(HttpURLConnection)urls[0].openConnection();
                if(connection.getResponseCode()> 300 && connection.getResponseCode()< 400) {
                    String redirectHeader = connection.getHeaderField("Location");
                    if (!TextUtils.isEmpty(redirectHeader)) {
                        URL newUrl = new URL(redirectHeader);
                        connection.disconnect();
                        connection = (HttpURLConnection) newUrl.openConnection();
                    }
                }
                //connection.setInstanceFollowRedirects(false); //should follow redirects
                StringBuilder builder=new StringBuilder();
                InputStream inputStream=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                String line=reader.readLine();
                while(line!=null){
                    builder.append(line);
                    line=reader.readLine();
                }
                result=new JSONObject(builder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null){
                    connection.disconnect();
                }
            }
            return result;
        }
    }
    public static JSONObject getJson()  {
        JSONObject jsonObject=null;
        URL url=buildURL();
        if(url==null){
            return null;
        }
        try {
            jsonObject=new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
