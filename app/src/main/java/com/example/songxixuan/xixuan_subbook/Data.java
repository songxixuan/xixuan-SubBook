package com.example.songxixuan.xixuan_subbook;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by songxixuan on 2018/2/3.
 */

public class Data {
    private static final Data ourInstance = new Data();

    private ArrayList<Subscription> subscriptions;

    public static Data getInstance() {
        return ourInstance;
    }



    private Data() {
        subscriptions = new ArrayList<Subscription>();
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public static void SaveInFile(Context context){
        Gson gson = new Gson();
        gson.toJson(ourInstance);
    }
}
