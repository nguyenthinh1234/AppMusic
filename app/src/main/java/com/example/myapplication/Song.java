package com.example.myapplication;

import java.io.Serializable;

public class Song implements Serializable {

    private int imgSong;
    private int songResource;
    private String songName;
    private String singerName;

    public Song(int imgSong, int songResource, String songName, String singerName) {
        this.imgSong = imgSong;
        this.songResource = songResource;
        this.songName = songName;
        this.singerName = singerName;
    }

    public int getImgSong() {
        return imgSong;
    }

    public void setImgSong(int imgSong) {
        this.imgSong = imgSong;
    }

    public int getSongResource() {
        return songResource;
    }

    public void setSongResource(int songResource) {
        this.songResource = songResource;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
