package com.zte.esapp.inter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.zte.esapp.util.ACache;

/**
 * Created by Administrator on 2017/8/17.
 */
public class BaseActivity extends Activity{

    public ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCache = ACache.get(this);
    }

    public void back(View view){
        onBackPressed();
    }
}
