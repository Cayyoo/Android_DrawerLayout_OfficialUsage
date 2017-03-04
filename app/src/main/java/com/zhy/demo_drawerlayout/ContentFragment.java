package com.zhy.demo_drawerlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 填充FrameLayout的内容
 * 使用v4包中的Fragment
 */
public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,null);
        TextView tv= (TextView) view.findViewById(R.id.tv);

        Bundle bundle=getArguments();
        String text=bundle.getString("text");
        tv.setText(text);

        return view;
    }

}
