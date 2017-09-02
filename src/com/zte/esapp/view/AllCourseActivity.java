package com.zte.esapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.zte.esapp.R;
import com.zte.esapp.controller.MyCourseAdapter;
import com.zte.esapp.controller.RemoteHandler;
import com.zte.esapp.inter.BaseActivity;
import com.zte.esapp.model.CourseTop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class AllCourseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv_course;
    private MyCourseAdapter mAdapter;

    private List<CourseTop> mDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        lv_course = (ListView) findViewById(R.id.lv_course);
        initData();


    }

    private void initData(){
        mDataList = new ArrayList<>();
        GetCourseAsyncTask task = new GetCourseAsyncTask();
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,CourseDetailActivity.class);
        intent.putExtra("courseTop",mDataList.get(position));
        startActivity(intent);
    }

    class GetCourseAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(final Void... unused) {
            try {
                JsonArray pArray = RemoteHandler.getCourseList();
                Gson gson = new Gson();

                int count = pArray.size();
                for (int i = 0; i < count; i++) {
                    CourseTop geDanGeInfo = gson.fromJson(pArray.get(i), CourseTop.class);
                    mDataList.add(geDanGeInfo);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean complete) {

            mAdapter = new MyCourseAdapter(AllCourseActivity.this,mDataList,lv_course);
            lv_course.setAdapter(mAdapter);
            lv_course.setOnItemClickListener(AllCourseActivity.this);

        }
    }

}