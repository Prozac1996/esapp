package com.zte.esapp.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.zte.esapp.R;
import com.zte.esapp.controller.RemoteHandler;

/**
 * Created by Administrator on 2017/8/19.
 */

public class RegisterActivity extends Activity {

    private EditText et_usernmae,et_password,et_password_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_usernmae = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
    }

    public void register(View view){
        String username = et_usernmae.getText().toString();
        String password = et_password.getText().toString();
        String passwordAgain = et_password_again.getText().toString();
        if(username.equals("") || password.equals("") || password.equals("")){
            Toast.makeText(RegisterActivity.this, "任何一项都不能为空！", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(!password.equals(passwordAgain)){
            Toast.makeText(RegisterActivity.this, "两次密码输入不一样！", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterAsyncTask registerTask = new RegisterAsyncTask();
        registerTask.execute(username,password);

    }

    class RegisterAsyncTask extends AsyncTask<String,Void,JsonObject>{


        @Override
        protected JsonObject doInBackground(String... params) {
            return RemoteHandler.register(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(JsonObject jsonObject) {
            super.onPostExecute(jsonObject);
            String result = jsonObject.get("result").getAsString();
            if(result.equals("1"))
                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            else if(result.equals("-1"))
                Toast.makeText(RegisterActivity.this, "手机号已存在！", Toast.LENGTH_SHORT).show();
        }
    }
}
