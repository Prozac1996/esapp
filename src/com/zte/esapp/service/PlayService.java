package com.zte.esapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import com.zte.esapp.controller.RemoteHandler;
import com.zte.esapp.model.CourseContent;
import com.zte.esapp.model.PlayInfo;


/**
 * Created by Administrator on 2017/8/22.
 */

public class PlayService extends Service {

    private static MediaPlayer mediaPlayer = new MediaPlayer();
    private static PlayInfo mPlayInfo = new PlayInfo();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mediaPlayer.isPlaying()){
            stop();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static void loadSong(CourseContent courseContent){
        mPlayInfo.setSongId(courseContent.getContentId());
        mPlayInfo.setSongName(courseContent.getContentTitle());
        mPlayInfo.setPath(RemoteHandler.getMusicPath(courseContent.getContentFile()));
        play(0);
//        int msg = intent.getIntExtra("msg",0);
//        if(msg == AppConstant.PlayMsg.PLAY_MSG){
//            play(0);
//        }else if(msg == AppConstant.PlayMsg.PAUSE_MSG){
//            pause();
//        }else if(msg == AppConstant.PlayMsg.STOP_MSG){
//            stop();
//        }
    }

    public static void play(int position){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mPlayInfo.getPath());
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void pause(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            mPlayInfo.setPlaying(false);
        }
    }

    public static PlayInfo toggle(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            mPlayInfo.setPlaying(false);
        }else{
            mediaPlayer.start();
            mPlayInfo.setPlaying(true);
        }
        return mPlayInfo;
    }

    public static void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mPlayInfo.setPlaying(false);
            try{
                mediaPlayer.prepare();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static PlayInfo getPlayInfo(){
        return mPlayInfo;
    }


    private static class PreparedListener implements MediaPlayer.OnPreparedListener {

        private int mPosition;

        public PreparedListener(int position) {
            this.mPosition = position;
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
            if(mPosition > 0){
                mediaPlayer.seekTo(mPosition);
            }
            mPlayInfo.setPlaying(true);
        }
    }


}
