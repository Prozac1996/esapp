package com.zte.esapp.view;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.model.PlayInfo;
import com.zte.esapp.service.PlayBroadcastReceiver;
import com.zte.esapp.service.PlayService;

/**
 * Created by Administrator on 2017/8/22.
 */

public class QuickControlFragment extends Fragment implements View.OnClickListener,PlayBroadcastReceiver.PlayListener{

    private ImageView btn_control;
    private TextView tv_songTitle, tv_author;
    private ProgressBar mProgressBar;
    private PlayBroadcastReceiver receiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quick_control_bar, container, false);
        btn_control = (ImageView) view.findViewById(R.id.btn_control);
        btn_control.setOnClickListener(this);
        tv_songTitle = (TextView) view.findViewById(R.id.tv_songTitle);
        tv_author = (TextView) view.findViewById(R.id.tv_author);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        receiver = new PlayBroadcastReceiver(this);

        IntentFilter filter = new IntentFilter("cn.zte.play");
        getActivity().registerReceiver(receiver,filter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        PlayInfo playInfo = PlayService.getPlayInfo();

        if (playInfo.isPlaying()) {
            tv_songTitle.setText(playInfo.getSongName());
            btn_control.setImageResource(R.drawable.pause_button);
        } else {
            tv_songTitle.setText(playInfo.getSongName());
            btn_control.setImageResource(R.drawable.play_button);
        }

        mProgressBar.setProgress(50);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_control:
                PlayInfo info = PlayService.toggle();
                if (info.isPlaying()) {
                    btn_control.setImageResource(R.drawable.pause_button);
                } else {
                    btn_control.setImageResource(R.drawable.play_button);
                }
                break;
        }
    }

    @Override
    public void onPlay() {
        PlayInfo playInfo = PlayService.getPlayInfo();

        if (playInfo.isPlaying()) {
            tv_songTitle.setText(playInfo.getSongName());
            btn_control.setImageResource(R.drawable.pause_button);
        } else {
            tv_songTitle.setText(playInfo.getSongName());
            btn_control.setImageResource(R.drawable.play_button);
        }
    }
}
