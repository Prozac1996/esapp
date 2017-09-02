package com.zte.esapp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.controller.MainFragmentAdapter;
import com.zte.esapp.inter.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class DownloadActivity extends BaseActivity implements View.OnClickListener {

    private TextView btn_already_download,btn_downloading;
    private ViewPager vp_download;
    private MainFragmentAdapter mAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_download);
        btn_already_download = (TextView) findViewById(R.id.btn_already_download);
        btn_downloading = (TextView) findViewById(R.id.btn_downloading);
        btn_already_download.setOnClickListener(this);
        btn_downloading.setOnClickListener(this);

        initViewPager();
    }

    private void initViewPager() {
        vp_download = (ViewPager) findViewById(R.id.vp_download);
        mFragments = new ArrayList<>();
        mFragments.add(new AlreadyDownloadFragment());
        mFragments.add(new DownloadingFragment());
        mAdapter = new MainFragmentAdapter(getFragmentManager(),mFragments);
        vp_download.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        btn_already_download.setSelected(false);
        btn_downloading.setSelected(false);
        switch (v.getId()){
            case R.id.btn_already_download:
                btn_already_download.setSelected(true);
                vp_download.setCurrentItem(0);
                break;
            case R.id.btn_downloading:
                btn_downloading.setSelected(true);
                vp_download.setCurrentItem(1);
                break;
        }
    }
}
