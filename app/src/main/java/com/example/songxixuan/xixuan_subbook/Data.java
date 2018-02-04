package com.example.songxixuan.xixuan_subbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

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



 /**

    public static void saveInFile(Context context){
        Gson gson = new Gson();
        gson.toJson(ourInstance);
    }
  */

 public static void loadFromFile(Context context) {

     try {
         FileInputStream fis = context.openFileInput(FILENAME);
         BufferedReader in = new BufferedReader(new InputStreamReader(fis));

         Gson gson = new Gson();

         Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
         subscriptions = gson.fromJson(in, listType);



     } catch (FileNotFoundException e) {
         subscriptions = new ArrayList<Subscription>();

     } catch (IOException e) {
         throw new RuntimeException();
     }

 }

    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(ourInstance, out);
            out.flush();


        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
