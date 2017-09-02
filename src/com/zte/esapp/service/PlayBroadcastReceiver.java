package com.zte.esapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/8/25.
 */
public class PlayBroadcastReceiver extends BroadcastReceiver {

    private PlayListener mListener;

    public PlayBroadcastReceiver() {
    }

    public PlayBroadcastReceiver(PlayListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(mListener != null)
            mListener.onPlay();
    }

    public interface PlayListener{

        void onPlay();
    }
}
