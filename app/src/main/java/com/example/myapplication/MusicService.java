package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    public static MediaPlayer mediaPlayer;
    MyService  service= new MyService();

    public class MyService  extends Binder{
        MusicService getService() {
            return MusicService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service","onBind");
        return service;
    }

    @Override
    public void onCreate() {
        Log.e("service","onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("service","onDestroy");
        super.onDestroy();
        if (mediaPlayer  !=null){
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }
    public  void Start(int resource){
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(getApplicationContext(), resource);
            }
            mediaPlayer.start();
    }

}
