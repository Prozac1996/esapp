package com.zte.esapp.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.zte.esapp.R;
import com.zte.esapp.widget.CircleImageView;

/**
 * Created by Administrator on 2017/8/16.
 */
public class MyFragment extends Fragment implements View.OnClickListener {

    private LinearLayout btn_message_center,btn_downloaded,btn_like,btn_account,btn_setting,btn_share;
    private CircleImageView btn_modify_head;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_main,null,false);
        btn_message_center = (LinearLayout) view.findViewById(R.id.btn_message_center);
        btn_downloaded = (LinearLayout) view.findViewById(R.id.btn_downloaded);
        btn_like = (LinearLayout) view.findViewById(R.id.btn_like);
        btn_account = (LinearLayout) view.findViewById(R.id.btn_account);
        btn_setting = (LinearLayout) view.findViewById(R.id.btn_setting);
        btn_share = (LinearLayout) view.findViewById(R.id.btn_share);
        btn_modify_head = (CircleImageView) view.findViewById(R.id.btn_modify_head);
        btn_modify_head.setOnClickListener(this);
        btn_message_center.setOnClickListener(this);
        btn_downloaded.setOnClickListener(this);
        btn_like.setOnClickListener(this);
        btn_account.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_modify_head:
                intent = new Intent(getActivity(),EditActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_message_center:
                intent = new Intent(getActivity(),MessageCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_downloaded:
                intent = new Intent(getActivity(),DownloadActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_like:
                intent = new Intent(getActivity(),LikeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_account:
                intent = new Intent(getActivity(),AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_setting:
                intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_share:
                break;
        }
    }
}
