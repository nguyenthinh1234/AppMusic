package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView imgSong;
    private TextView tv_NameSong, tv_NameSinger, tv_TimePlay, tv_TimeEnd;
    private Song mSong;
    private  boolean isPlaying;
    private ImageView imgPlay_or_Pause;
    private SeekBar seekBar;
    private MusicService musicService;
    private Handler handler;
    private ServiceConnection connection =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyService myService = (MusicService.MyService) service;
            musicService = myService.getService();
            playMusic();
            isPlaying= true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isPlaying= false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSong = (Song) getIntent().getExtras().get("song");
        handler =  new Handler();
        initUI();
        setData();
    }

    private void setData() {
        imgSong.setImageResource(mSong.getImgSong());
        tv_NameSong.setText(mSong.getSongName());
        tv_NameSinger.setText(mSong.getSingerName());
    }

    private void initUI() {
        imgSong = findViewById(R.id.img_Song);
        tv_NameSinger = findViewById(R.id.Singer_Name);
        tv_NameSong = findViewById(R.id.Song_Name);
        seekBar = findViewById(R.id.play_music);
        tv_TimePlay = findViewById(R.id.tv_Time_Playing);
        tv_TimeEnd = findViewById(R.id.tv_TimeEnd);
        imgPlay_or_Pause= findViewById(R.id.img_play_or_pause);
//        imgPlay_or_Pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (MusicService.mediaPlayer.isPlaying()){
//                    imgPlay_or_Pause.setImageResource(R.drawable.ic_play);
//                    MusicService.mediaPlayer.stop();
//                }else{
//                    imgPlay_or_Pause.setImageResource(R.drawable.ic_pause);
//                    MusicService.mediaPlayer.start();
//                }
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent =  new Intent(this, MusicService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }
    public  void playMusic(){
        musicService.Start(mSong.getSongResource());
        seekBar.setMax(MusicService.mediaPlayer.getDuration());
        tv_TimeEnd.setText(TimeSong(mSong.getSongResource()));
        update();
    }

    private void update() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss") ;
                    seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
                    tv_TimePlay.setText(dateFormat.format(MusicService.mediaPlayer.getCurrentPosition()));
                handler.postDelayed(this, 500);
            }
        }, 500);
    }
    private String  TimeSong(int Song){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss") ;
        String timeSong =  dateFormat.format(MusicService.mediaPlayer.getDuration());
        return timeSong;
    }
}