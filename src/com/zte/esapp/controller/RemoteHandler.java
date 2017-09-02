package com.zte.esapp.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/19.
 */

public class RemoteHandler {
    public static final int GET_COURSE_LIST = 520;
    public static final int GET_COURSE_CONTENT = 260;

    private static String serverAddress = "http://120.27.40.216:6677";
//    private static String serverAddress = "http://192.168.1.100:8080";

    public static JsonArray getCourseList(){
        String path = serverAddress+"/coursemanage/android/getCourseList";
        return action(path);

    }

    public static JsonObject getCourseContent(UUID id){
        String path = serverAddress+"/coursemanage/android/getCourseContent?id="+id;
//        action(GET_COURSE_CONTENT,url,handler);
       return action2(path);
    }

    public static JsonArray getExpertCourse(UUID expertId){
        String path = serverAddress+"/coursemanage/android/getExpertCourse?id="+expertId;
        return action(path);
    }

    private static JsonObject action2(String path){
        URL url = null;
        try {
            url = new URL(path);
            URLConnection rulConnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            httpUrlConnection.setConnectTimeout(30000);
            httpUrlConnection.setReadTimeout(30000);
            httpUrlConnection.connect();
            InputStream is = httpUrlConnection.getInputStream();
            JsonParser parser = new JsonParser();
            JsonElement el = parser.parse(InputStreamToString(is));
            return el.getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JsonArray action(String path){
        URL url = null;
        try {
            url = new URL(path);

            URLConnection rulConnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            httpUrlConnection.setConnectTimeout(30000);
            httpUrlConnection.setReadTimeout(30000);
            httpUrlConnection.connect();
            InputStream is = httpUrlConnection.getInputStream();
            JsonParser parser = new JsonParser();
            JsonElement el = parser.parse(InputStreamToString(is));
            return el.getAsJsonArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMusicPath(String url){
        return serverAddress+url;
    }

    public static String getAddress(){
        return serverAddress;
    }

    public static Bitmap getBitmap(String imageUrl){
        imageUrl = "http://"+ serverAddress + imageUrl;
        Bitmap mBitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            mBitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mBitmap;
    }

    private static String InputStreamToString(InputStream is){
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while((line = br.readLine())!= null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = sb.toString();
        if (str != null && str.startsWith("\ufeff")) {
            str = str.substring(1);
        }
        return str;
    }


    public static JsonObject register(String username, String password) {
        String path = serverAddress+"/coursemanage/android/register?phone="+username+"&password="+password;
        return action2(path);
    }
}
