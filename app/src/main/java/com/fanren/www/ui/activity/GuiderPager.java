package com.fanren.www.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanren.www.R;
import com.fanren.www.adapter.ViewPagerAdapter;
import com.fanren.www.basic.JerryActivity;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:      JerryChow
 * Date:        2017/5/8 14:37
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 引导页
 */
public class GuiderPager extends JerryActivity {
    @BindView(R.id.tv_experience)
    TextView tv_experience;
    @BindView(R.id.viewPager_guider)
    ViewPager viewPagerGuider;
    private int[] iv_ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @Override
    protected int inflateLayout() {
        return R.layout.activity_guider_pager;
    }

    @Override
    protected void initIntentData(Bundle var1) {

    }

    @Override
    protected JerryFragment initFragment() {
        return null;
    }

    @Override
    protected void doOperate() {

        List<ImageView> iv_list = new ArrayList<>();
        for (int i = 0; i < iv_ids.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.layout_guide_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_guider_item);
            imageView.setImageResource(iv_ids[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            iv_list.add(imageView);
        }
        viewPagerGuider.setAdapter(new ViewPagerAdapter(this, iv_list));
        viewPagerGuider.setCurrentItem(0);
        viewPagerGuider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                                    @Override
                                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                                    }

                                                    @Override
                                                    public void onPageSelected(int position) {
                                                        LogUtil.d("viewpager:position" + position);
                                                        if (position == iv_ids.length - 1) {
                                                            tv_experience.setVisibility(View.VISIBLE);
                                                        } else {
                                                            tv_experience.setVisibility(View.INVISIBLE);
                                                        }
                                                    }

                                                    @Override
                                                    public void onPageScrollStateChanged(int state) {

                                                    }
                                                }
        );
    }

    @OnClick(R.id.tv_experience)
    public void onClick(View view) {
        startActivity(LoginActivity.class, null, true);
        LogUtil.d("跳转登录页面");
    }
}
