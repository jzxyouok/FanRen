package com.fanren.www.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.fanren.www.app.JerryApp;
import com.fanren.www.util.CommonUtil;
import com.fanren.www.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class JerryActivity extends AppCompatActivity {

    private JerryFragment currentViewFr;
    private Unbinder unbinder;
    private int mMainViewId;

    protected abstract int inflateLayout();

    protected abstract void initIntentData(Bundle var1);    //初始化传递数据

    protected abstract JerryFragment initFragment();    //初始化fragment

    protected abstract void doOperate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentData(savedInstanceState);
        if ((null != initFragment()) && (inflateLayout() == 0)) {
            this.mMainViewId = CommonUtil.generateViewId();
            RelativeLayout layout = new RelativeLayout(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
            layout.setLayoutParams(params);
            layout.setId(this.mMainViewId);
            this.setContentView(layout);
            this.replaceFragment(mMainViewId, initFragment(), initFragment().getClass().getSimpleName());
        } else {
            setContentView(inflateLayout());

        }
        unbinder = ButterKnife.bind(this);
        JerryApp.addActivity(this);
        doOperate();
    }

    public void replaceFragment(int id, JerryFragment fragment, String tag) {
        LogUtil.d("切换fragment " + tag);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView()
                    .getWindowToken(), 0);
        }
        if (fragment == currentViewFr) {
            return;
        }
        FragmentTransaction mFragmentTransaction;
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        if (currentViewFr != null) {
            mFragmentTransaction.hide(currentViewFr);
        }
        if (fragment.isAdded()) {
            mFragmentTransaction.show(fragment);
        } else {
            mFragmentTransaction.replace(id, fragment, tag);
        }
        currentViewFr = fragment;
        mFragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        JerryApp.finishActivity(this);
    }

    public int getMainViewId() {
        return this.mMainViewId;
    }

    /**
     * 跳转activity
     *
     * @param target
     * @param bundle
     * @param finish
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }

    //隐藏标题
    protected void hindTitleBar(View view) {
        view.setVisibility(View.GONE);
    }

    //显示标题
    protected void showTitleBar(View view) {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            LogUtil.d("返回键被按了");
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
