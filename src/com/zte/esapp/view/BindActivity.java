package com.zte.esapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zte.esapp.R;
import com.zte.esapp.inter.BaseActivity;

/**
 * Created by Administrator on 2017/8/17.
 */
public class BindActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bind);
    }

    public void modifyPassword(View view){
        Intent intent = new Intent(this,ModifyPasswordActivity.class);
        startActivity(intent);
    }
}