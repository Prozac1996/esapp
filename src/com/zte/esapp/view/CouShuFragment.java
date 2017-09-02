package com.zte.esapp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zte.esapp.R;

/**
 * Created by Administrator on 2017/8/16.
 */
public class CouShuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_choushu,null,false);
        return view;
    }
}
