package com.fanren.www.ui.activity;

import android.os.Bundle;

import com.fanren.www.basic.JerryActivity;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.ui.fragment.LoginFragment;
import com.fanren.www.util.CommonUtil;

/**
 * Author:      JerryChow
 * Date:        2017/3/29 15:01
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:     登录界面
 */
public class LoginActivity extends JerryActivity {
    @Override
    protected int inflateLayout() {
        return 0;
    }

    @Override
    protected void initIntentData(Bundle var1) {

    }

    @Override
    protected JerryFragment initFragment() {
        CommonUtil.setFullScreen(this);
        return new LoginFragment();
    }

    @Override
    protected void doOperate() {

    }
}
