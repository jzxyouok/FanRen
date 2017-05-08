package com.fanren.www.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> iv_list;
    private Context context;


    public ViewPagerAdapter(Context context, List<ImageView> list){
        this.iv_list=list;
        this.context=context;

    }

    public ViewPagerAdapter(Context context){
//        this.iv_list=list;
        this.context=context;

    }
        @Override
        public int getCount() {

            //实现无限循环轮播return Integer.MAX_VALUE;
            return iv_list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           //实现轮播 View view = iv_list.get(position % iv_list.size());
            View view = iv_list.get(position);
            if (view.getParent() != null) {
                ViewGroup vg = (ViewGroup) view.getParent();
                vg.removeView(view);
            }
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //实现轮播 container.removeView(iv_list.get(position % iv_list.size()));
            container.removeView(iv_list.get(position));
        }
    }