package com.zte.esapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.zte.esapp.R;
import com.zte.esapp.controller.MainFragmentAdapter;
import com.zte.esapp.controller.NoScrollViewPager;
import com.zte.esapp.inter.BaseActivity;
import com.zte.esapp.service.PlayService;
import com.zte.esapp.util.Config;
import com.zte.esapp.util.NetworkTool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private static final String TAG = "Update";
    public ProgressDialog pBar;
    private Handler handler = new Handler();

    private int newVerCode = 0;
    private String newVerName = "";
    private NoScrollViewPager mViewPager;
    private MainFragmentAdapter mAdapter;
    private List<Fragment> mFragments;

    private ImageView btn_discovery,btn_what,btn_up_class,btn_bought,btn_my;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initVersionCotrol();
        initView();


        Intent intent = new Intent(this, PlayService.class);
        intent.setClass(MainActivity.this, PlayService.class);
        startService(intent);
    }

    private void initVersionCotrol() {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask();
        updateAsyncTask.execute();
    }

    /**
     * 获取APP版本号
     * @return
     */
    private boolean getServerVerCode() {
        try {
            String verjson = NetworkTool.getContent(Config.UPDATE_SERVER
                    + Config.UPDATE_VERJSON);
            JSONArray array = new JSONArray(verjson);
            if (array.length() > 0) {
                JSONObject obj = array.getJSONObject(0);
                try {
                    newVerCode = Integer.parseInt(obj.getString("verCode"));
                    newVerName = obj.getString("verName");
                } catch (Exception e) {
                    newVerCode = -1;
                    newVerName = "";
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    private void notNewVersionShow() {
        int verCode = Config.getVerCode(this);
        String verName = Config.getVerName(this);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(verName);
        sb.append(" Code:");
        sb.append(verCode);
        sb.append(",\n已是最新版,无需更新!");
        Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("软件更新").setMessage(sb.toString())// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }

                        }).create();// 创建
        // 显示对话框
//        dialog.show();
    }

    private void doNewVersionUpdate() {
        int verCode = Config.getVerCode(this);
        String verName = Config.getVerName(this);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(verName);
        sb.append(" Code:");
        sb.append(verCode);
        sb.append(", 发现新版本:");
        sb.append(newVerName);
        sb.append(" Code:");
        sb.append(newVerCode);
        sb.append(", 是否更新?");
        Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("软件更新")
                .setMessage(sb.toString())
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                pBar = new ProgressDialog(MainActivity.this);
                                pBar.setTitle("正在下载");
                                pBar.setMessage("请稍候...");
                                pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                downFile(Config.UPDATE_SERVER
                                        + Config.UPDATE_APKNAME);
                            }

                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    void downFile(final String url) {
        pBar.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {

                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                Config.UPDATE_SAVENAME);
                        fileOutputStream = new FileOutputStream(file);

                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                            }
                        }

                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }

    void down() {
        handler.post(new Runnable() {
            public void run() {
                pBar.cancel();
                update();
            }
        });

    }

    void update() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), Config.UPDATE_SAVENAME)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
    private void initView() {
        mViewPager = (NoScrollViewPager) findViewById(R.id.main_viewPager);
        mFragments = new ArrayList<>();
        mFragments.add(new DiscoveryFragment());
        mFragments.add(new CouShuFragment());
        mFragments.add(new CouShuFragment());
        mFragments.add(new BoughtFragment());
        mFragments.add(new MyFragment());
        mAdapter = new MainFragmentAdapter(getFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        btn_discovery = (ImageView) findViewById(R.id.btn_discovery);
        btn_bought = (ImageView) findViewById(R.id.btn_bought);
        btn_what = (ImageView) findViewById(R.id.btn_what);
        btn_up_class = (ImageView) findViewById(R.id.btn_up_class);
        btn_my = (ImageView) findViewById(R.id.btn_my);

        btn_discovery.setOnClickListener(this);
        btn_bought.setOnClickListener(this);
        btn_what.setOnClickListener(this);
        btn_up_class.setOnClickListener(this);
        btn_my.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        changeTab(v);
        switch (v.getId()){
            case R.id.btn_discovery:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btn_what:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.btn_up_class:
                break;
            case R.id.btn_bought:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.btn_my:
                mViewPager.setCurrentItem(4);
                break;
        }
    }

    private void changeTab(View view){
        btn_discovery.setSelected(false);
        btn_bought.setSelected(false);
        btn_what.setSelected(false);
        btn_up_class.setSelected(false);
        btn_my.setSelected(false);
        view.setSelected(true);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        finish();
    }


    class UpdateAsyncTask extends AsyncTask<Void,Void,Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            boolean needUpdate = getServerVerCode();
            return needUpdate;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                int vercode = Config.getVerCode(MainActivity.this);
                if (newVerCode > vercode) {
//                    doNewVersionUpdate();
                } else {
                    notNewVersionShow();
                }
            }
        }
    }

}
