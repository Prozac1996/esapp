package com.zte.esapp.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.zte.esapp.R;
import com.zte.esapp.model.CourseTop;
import com.zte.esapp.util.ACache;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */

public class CourseTopAdapter extends BaseAdapter{

    private Context mContext;
    private List<CourseTop> mData;
    private AsyncImageLoader asyncImageLoader;
    private ListView listView;

    private ACache mCache;


    public CourseTopAdapter(Context context, List<CourseTop> mData, ListView listView) {
        this.mData = mData;
        mContext = context;
        this.listView = listView;
        asyncImageLoader = new AsyncImageLoader();
        mCache = ACache.get(context);

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
    public View getView(int position, View convertView , ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_course_top,viewGroup,false);
            holder = new ViewHolder();
            holder.course_image = (ImageView) convertView.findViewById(R.id.course_image);
            holder.course_author = (TextView) convertView.findViewById(R.id.course_author);
            holder.course_title = (TextView) convertView.findViewById(R.id.course_title);
            holder.course_price = (TextView) convertView.findViewById(R.id.course_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CourseTop courseTop = mData.get(position);
//        image
        if(courseTop.getCourseCover()!= null){
            Drawable cachedImage = mCache.getAsDrawable(RemoteHandler.getAddress() + courseTop.getCourseCover());
            if(cachedImage == null) {
                holder.course_image.setTag(RemoteHandler.getAddress() + courseTop.getCourseCover());
                asyncImageLoader.loadDrawable(RemoteHandler.getAddress() + courseTop.getCourseCover(), new AsyncImageLoader.ImageCallback() {
                    public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                        ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
                        if (imageViewByTag != null) {
                            imageViewByTag.setImageDrawable(imageDrawable);
                            mCache.put(RemoteHandler.getAddress() + courseTop.getCourseCover(),imageDrawable);
                        }
                    }
                });

            }else{
                holder.course_image.setImageDrawable(cachedImage);
            }
        }

        holder.course_author.setText(courseTop.getExpertName());
        holder.course_title.setText(courseTop.getCourseName());
        holder.course_price.setText(courseTop.getCoursePrice()+"");
        return convertView;
    }

    public class ViewHolder{
        private ImageView course_image;
        private TextView course_title,course_author,course_price;
    }


}
