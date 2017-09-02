package com.zte.esapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.model.CourseTop;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */
public class TeacherCourseAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourseTop> mData;

    public TeacherCourseAdapter(Context mContext, List<CourseTop> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_teacher_course,parent,false);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_createTime = (TextView) convertView.findViewById(R.id.tv_createTime);
            holder.tv_people = (TextView) convertView.findViewById(R.id.tv_people);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CourseTop courseTop = mData.get(position);
        holder.tv_title.setText(courseTop.getCourseName());
        holder.tv_createTime.setText(courseTop.getCourseCreateTime());
        holder.tv_people.setText(courseTop.getCoursePeople()+"");

        return convertView;
    }

    public class ViewHolder{
        TextView tv_title,tv_createTime,tv_people;
    }
}
