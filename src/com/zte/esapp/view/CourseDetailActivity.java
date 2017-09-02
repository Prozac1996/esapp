package com.zte.esapp.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zte.esapp.R;
import com.zte.esapp.controller.AsyncImageLoader;
import com.zte.esapp.controller.ContentAdapter;
import com.zte.esapp.controller.RemoteHandler;
import com.zte.esapp.inter.BaseActivity;
import com.zte.esapp.model.CourseContent;
import com.zte.esapp.model.CourseTop;
import com.zte.esapp.model.Expert;
import com.zte.esapp.service.PlayService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class CourseDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private TextView course_name, course_author, course_discription, course_people, course_num;
    private CourseTop mCourseTop;
    private Expert mExpert;
    private ListView lv_try_listen;
    private ContentAdapter mAdapter;
    private RelativeLayout rl_bg;

    private List<CourseContent> mList;

    private getContentsAsyncTask mLoadNetList;

    private AsyncImageLoader asyncImageLoader = new AsyncImageLoader();



//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            try {
//                switch (msg.what) {
//                    case RemoteHandler.GET_COURSE_CONTENT:
//                        JSONArray array = new JSONArray(String.valueOf(msg.obj));
//                        JSONObject obj = (JSONObject) array.get(0);
//                        CourseContent content = CourseContent.fromJson(obj);
////                        mContentList.add(content);
//                        mAdapter.notifyDataSetChanged();
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        setContentView(R.layout.activity_course_detail);
        course_name = (TextView) findViewById(R.id.course_name);
        course_author = (TextView) findViewById(R.id.course_author);
        course_discription = (TextView) findViewById(R.id.course_discription);
        course_people = (TextView) findViewById(R.id.course_people);
        course_num = (TextView) findViewById(R.id.course_num);
        lv_try_listen = (ListView) findViewById(R.id.lv_try_listen);
        rl_bg = (RelativeLayout) findViewById(R.id.rl_bg);
        mList = new ArrayList<>();
        Intent intent = getIntent();
        mCourseTop = (CourseTop) intent.getSerializableExtra("courseTop");


        if(mCourseTop.getCourseCover()!= null){
            Drawable cachedImage = mCache.getAsDrawable(RemoteHandler.getAddress() + mCourseTop.getCourseCover());
            if(cachedImage == null) {
                asyncImageLoader.loadDrawable(RemoteHandler.getAddress() + mCourseTop.getCourseCover(), new AsyncImageLoader.ImageCallback() {
                    public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                        if (rl_bg != null) {
                            rl_bg.setBackground(imageDrawable);
                            mCache.put(RemoteHandler.getAddress() + mCourseTop.getCourseCover(), imageDrawable);
                        }
                    }
                });
            }else{
                rl_bg.setBackground(cachedImage);
            }
        }

        course_name.setText(mCourseTop.getCourseName());

        course_people.setText(mCourseTop.getCoursePeople() + "");
        course_num.setText(mCourseTop.getCourseNum() + "");

        mLoadNetList = new getContentsAsyncTask();
        mLoadNetList.execute();


    }


    //查看作者信息
    public void authorDetail(View view) {
        Intent intent = new Intent(this, TeacherDetailActivity.class);
        intent.putExtra("expert",mExpert);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

        //点击课程内容之后后台线程调用服务开始播放
        new Thread(){
            @Override
            public void run() {
                super.run();
                CourseContent courseContent = mList.get(position);
                PlayService.loadSong(courseContent);

                Intent intent = new Intent();
                intent.setAction("cn.zte.play");
                sendBroadcast(intent);

            }
        }.start();
        Toast.makeText(CourseDetailActivity.this, "正在加载...", Toast.LENGTH_SHORT).show();


    }


    //获取信息的线程
    class getContentsAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... unused) {
            try {
                JsonObject jsonObject = RemoteHandler.getCourseContent(mCourseTop.getCourseId());
                JsonArray pArray = jsonObject.get("content").getAsJsonArray();
                JsonObject pObject = jsonObject.get("expert").getAsJsonObject();


                Gson gson = new Gson();

                mExpert = gson.fromJson(pObject,Expert.class);
                int count = pArray.size();
                for (int i = 0; i < count; i++) {
                    CourseContent geDanGeInfo = gson.fromJson(pArray.get(i), CourseContent.class);
                    mList.add(geDanGeInfo);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        //加载完后页面刷新
        @Override
        protected void onPostExecute(Boolean complete) {

            mAdapter = new ContentAdapter(CourseDetailActivity.this, mList);
            lv_try_listen.setAdapter(mAdapter);
            lv_try_listen.setOnItemClickListener(CourseDetailActivity.this);

            course_author.setText(mExpert.getExpertName());
            course_discription.setText(mExpert.getExpertIntro());

        }
    }
}