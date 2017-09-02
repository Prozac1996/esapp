package com.zte.esapp.view;

import android.content.Intent;
import android.os.Bundle;
import com.zte.esapp.R;
import com.zte.esapp.inter.BaseActivity;

/**
 * Created by Administrator on 2017/8/19.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
