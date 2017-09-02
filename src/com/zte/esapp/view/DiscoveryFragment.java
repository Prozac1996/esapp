package com.zte.esapp.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.zte.esapp.R;
import com.zte.esapp.controller.CourseTopAdapter;
import com.zte.esapp.controller.RemoteHandler;
import com.zte.esapp.model.CourseTop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/16.
 */
public class DiscoveryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private LinearLayout btn_course;
//    private TextView btn_review;/

    private ViewPager vp_banner;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    private int oldPosition = 0;
    private List<CourseTop> mDataList;
    private CourseTopAdapter mDataAdapter;

    private int[] imageIds = new int[]{
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3
    };
    private BannerViewPagerAdapter mAdapter;
    private ScheduledExecutorService scheduledExecutorService;
    private ListView lv_course;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_discovery,null,false);
        btn_course = (LinearLayout) view.findViewById(R.id.btn_course);
//        btn_review = (TextView) view.findViewById(R.id.btn_review);
        btn_course.setOnClickListener(this);
//        btn_review.setOnClickListener(this);
        lv_course = (ListView) view.findViewById(R.id.lv_course);

        images = new ArrayList<>();
        for(int i = 0;i <imageIds.length ;i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        dots.add(view.findViewById(R.id.dot_3));
        dots.add(view.findViewById(R.id.dot_4));

        mAdapter = new BannerViewPagerAdapter();
        vp_banner = (ViewPager) view.findViewById(R.id.vp_banner);
        vp_banner.setAdapter(mAdapter);

        vp_banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                dots.get(position).setBackgroundResource(R.drawable.point_red);
                dots.get(oldPosition).setBackgroundResource(R.drawable.point_blue);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mDataList = new ArrayList<>();
        GetCourseAsyncTask task = new GetCourseAsyncTask();
        task.execute();

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
        intent.putExtra("courseTop",mDataList.get(position));
        startActivity(intent);
    }

    private class BannerViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            vp_banner.setCurrentItem(currentItem);
        };
    };

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_course:
                intent = new Intent(getActivity(),AllCourseActivity.class);
                startActivity(intent);
                break;
//            case R.id.btn_review:
//                intent = new Intent(getActivity(),ReviewActivity.class);
//                startActivity(intent);
//                break;
        }
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

            mDataAdapter = new CourseTopAdapter(getActivity(),mDataList,lv_course);
            lv_course.setAdapter(mDataAdapter);
            lv_course.setOnItemClickListener(DiscoveryFragment.this);

        }
    }

}
