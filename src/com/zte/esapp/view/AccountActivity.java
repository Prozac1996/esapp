package com.zte.esapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.inter.BaseActivity;

/**
 * Created by Administrator on 2017/8/16.
 */
public class AccountActivity extends BaseActivity implements View.OnClickListener {

    TextView btn_6,btn_68,btn_208,btn_388,btn_698,btn_998,tv_pay;

    int money = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        btn_6 = (TextView) findViewById(R.id.btn_6);
        btn_68 = (TextView) findViewById(R.id.btn_68);
        btn_208 = (TextView) findViewById(R.id.btn_208);
        btn_388 = (TextView) findViewById(R.id.btn_388);
        btn_698 = (TextView) findViewById(R.id.btn_698);
        btn_998 = (TextView) findViewById(R.id.btn_998);
        tv_pay = (TextView) findViewById(R.id.tv_pay);

        btn_6.setOnClickListener(this);
        btn_68.setOnClickListener(this);
        btn_208.setOnClickListener(this);
        btn_388.setOnClickListener(this);
        btn_698.setOnClickListener(this);
        btn_998.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        btn_6.setSelected(false);
        btn_68.setSelected(false);
        btn_208.setSelected(false);
        btn_388.setSelected(false);
        btn_698.setSelected(false);
        btn_998.setSelected(false);
        v.setSelected(true);
        switch (v.getId()){
            case R.id.btn_6:
                money = 6;
                break;
            case R.id.btn_68:
                money = 68;
                break;
            case R.id.btn_208:
                money = 208;
                break;
            case R.id.btn_388:
                money = 388;
                break;
            case R.id.btn_698:
                money = 698;
                break;
            case R.id.btn_998:
                money = 998;
                break;
        }
        tv_pay.setText(money+"");
    }
}