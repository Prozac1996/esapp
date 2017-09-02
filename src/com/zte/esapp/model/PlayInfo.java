package com.zte.esapp.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PlayInfo implements Serializable{

    //歌曲Id
    private UUID songId;
    private String songName;
    private boolean isPlaying = false;
    private String songAuthor;
    private String path;

    public PlayInfo() {
    }

    public UUID getSongId() {
        return songId;
    }

    public void setSongId(UUID songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
