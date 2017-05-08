package com.fanren.www.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanren.www.R;
import com.fanren.www.app.JerryApp;
import com.fanren.www.basic.JerryActivity;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.bean.JerryUser;
import com.fanren.www.util.CommonUtil;
import com.fanren.www.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Author:      JerryChow
 * Date:        2017/3/28 18:24
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:    广告页面
 */
public class SplashActivity extends JerryActivity {
    @BindView(R.id.tv_jump)
    TextView tv_jump;
    @BindView(R.id.iv_splash)
    RelativeLayout iv_splash;
    private CountDownTimer timer;
    private static final long TOTAL_TIME = 4000;
    private static final long STEP_TIME = 1000;
    @Override
    protected int inflateLayout() {
        CommonUtil.setFullScreen(this);
        return R.layout.layout_activity_splash;

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
        timer=new CountDownTimer(TOTAL_TIME,STEP_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_jump.setText(millisUntilFinished/STEP_TIME+"跳 过");

            }

            @Override
            public void onFinish() {
                jumpActivity();
            }
        };
        timer.start();
    }

    @OnClick(R.id.tv_jump)
    public void onClick(View view) {
        jumpActivity();
    }

    private void jumpActivity() {
        if(CommonUtil.isFirstLogin(JerryApp.getJerryApp())){
            startActivity(GuiderPager.class,null,true);
        }else{
            JerryUser currentUser= BmobUser.getCurrentUser(JerryUser.class);
            LogUtil.d("currentUser:      "+currentUser);
            if(currentUser==null){
                startActivity(LoginActivity.class,null,true);
            }else{
                //startActivity(IndexActivity.class,null,true);
                LogUtil.d("跳转首页");
            }
        }
        timer.cancel();
    }
}
