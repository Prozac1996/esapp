package com.zte.esapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zte.esapp.R;
import com.zte.esapp.inter.BaseActivity;

/**
 * Created by Administrator on 2017/8/16.
 */
public class SettingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
    }

    public void bind(View view){
        Intent intent = new Intent(this,BindActivity.class);
        startActivity(intent);
    }
}