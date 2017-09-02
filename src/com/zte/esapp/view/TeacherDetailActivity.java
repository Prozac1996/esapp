package com.zte.esapp.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.zte.esapp.R;
import com.zte.esapp.controller.AsyncImageLoader;
import com.zte.esapp.controller.RemoteHandler;
import com.zte.esapp.controller.TeacherCourseAdapter;
import com.zte.esapp.inter.BaseActivity;
import com.zte.esapp.model.CourseTop;
import com.zte.esapp.model.Expert;
import com.zte.esapp.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class TeacherDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Expert mExpert;
    private List<CourseTop> mCourseTopList;
    private TeacherCourseAdapter mAdapter;
    private ListView lv_course;
    private TextView tv_name,tv_intro,tv_num;
    private CircleImageView iv_head;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_teacher);
        Intent intent = getIntent();
        mExpert = (Expert) intent.getSerializableExtra("expert");
        lv_course = (ListView) findViewById(R.id.lv_course);
        mCourseTopList = new ArrayList<>();

        GetInfoAsyncTask getInfoAsyncTask = new GetInfoAsyncTask();
        getInfoAsyncTask.execute();


        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_num = (TextView) findViewById(R.id.tv_num);
        iv_head = (CircleImageView) findViewById(R.id.iv_head);

        tv_name.setText(mExpert.getExpertName());
        tv_intro.setText(mExpert.getExpertIntro());

        lv_course.setOnItemClickListener(this);

        if(mExpert.getExpertPicture()!= null){
            AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
            asyncImageLoader.loadDrawable(RemoteHandler.getAddress()+mExpert.getExpertPicture(),new AsyncImageLoader.ImageCallback() {
                public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                    if (iv_head != null) {
                        iv_head.setBackground(imageDrawable);
                    }
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,CourseDetailActivity.class);
        intent.putExtra("courseTop",mCourseTopList.get(position));
        startActivity(intent);
    }


    class GetInfoAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            JsonArray pArray = RemoteHandler.getExpertCourse(mExpert.getId());
            Gson gson = new Gson();

            int count = pArray.size();
            for (int i = 0; i < count; i++) {
                CourseTop geDanGeInfo = gson.fromJson(pArray.get(i), CourseTop.class);
                mCourseTopList.add(geDanGeInfo);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mAdapter = new TeacherCourseAdapter(TeacherDetailActivity.this, mCourseTopList);
            lv_course.setAdapter(mAdapter);

            tv_num.setText(mCourseTopList.size()+"");



        }
    }
}

