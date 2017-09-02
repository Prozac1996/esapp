package com.zte.esapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.model.CourseContent;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20.
 */

public class ContentAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourseContent> mData;

    public ContentAdapter(Context mContext, List<CourseContent> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail,viewGroup,false);
            holder = new ViewHolder();
            holder.content_length = (TextView) convertView.findViewById(R.id.content_length);
            holder.content_name = (TextView) convertView.findViewById(R.id.content_name);
            holder.content_people = (TextView) convertView.findViewById(R.id.content_people);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CourseContent courseContent = mData.get(i);
        holder.content_name.setText(courseContent.getContentTitle());
        holder.content_length.setText(courseContent.getContentFileLength());
        holder.content_people.setText("5");

        return convertView;
    }

    public class ViewHolder{
        TextView content_name,content_length,content_people;
    }
}
