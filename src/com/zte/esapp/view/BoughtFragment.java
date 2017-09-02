package com.zte.esapp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.zte.esapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
public class BoughtFragment extends Fragment {

    private ListView lv_bought;
    private SimpleAdapter mAdapter;

    private List<Map<String,Object>> mDataList;
    private String[] mStr = new String[]{"courseContent_name","courseContent_length","courseContent_already"};
    private int[] mPosition = new int[]{R.id.courseContent_name,R.id.courseContent_length,R.id.courseContent_already};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bought,null,false);
        lv_bought = (ListView) view.findViewById(R.id.lv_bought);
        initData();
        mAdapter = new SimpleAdapter(getActivity(),mDataList,R.layout.item_bought,mStr,mPosition);
        lv_bought.setAdapter(mAdapter);
        return view;
    }

    private void initData() {
        mDataList = new ArrayList<>();
        Map map = new HashMap<>();
        map.put(mStr[0],"中国有嘻哈");
        map.put(mStr[1],"78:25");
        map.put(mStr[2],"15:55");
        mDataList.add(map);

    }
}
