package com.fanren.www.ui.fragment;


import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.fanren.www.R;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.basic.UserManager;
import com.fanren.www.bean.JerryUser;
import com.fanren.www.event.FinishEvent;
import com.fanren.www.impl.OnRequestBmobListener;
import com.fanren.www.ui.activity.LoginActivity;
import com.fanren.www.util.CommonUtil;
import com.fanren.www.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;

/**
 * Author:      JerryChow
 * Date:        2017/3/29 15:06
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class RegisterFragment extends JerryFragment {
    @BindView(R.id.ed_name_group_register)
    TextInputLayout ed_name_group_register;
    @BindView(R.id.ed_password_group_register)
    TextInputLayout ed_password_group_register;
    @BindView(R.id.btn_register)
    Button btn_register;
    TextInputEditText editName;
    TextInputEditText editPassword;
    private LoginActivity activity;
    private static RegisterFragment loginFragment;

    public static RegisterFragment getInstance() {
        if (loginFragment == null) {
            loginFragment = new RegisterFragment();
        }
        return loginFragment;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.layout_fragment_register;
    }

    @Override
    protected void initView(View view) {
        activity = (LoginActivity) getBindActivity();
        editName = (TextInputEditText) ed_name_group_register.getEditText();
        editPassword = (TextInputEditText) ed_password_group_register.getEditText();
    }

    @Override
    protected void doOperate() {

    }

    @OnClick(R.id.btn_register)
    public void doRegister(View view) {
        String account = editName.getText().toString();
        String password = editPassword.getText().toString();
        LogUtil.d("username:" + account + "password:" + password);
        UserManager.getInstance().registerByPhone(account, password, new OnRequestBmobListener<JerryUser>() {
            @Override
            public void onSuccess(JerryUser user) {
                CommonUtil.showLong("恭喜用户" + user.getMobilePhoneNumber() + "注册成功:");
                LogUtil.d("恭喜用户" + user.getMobilePhoneNumber() + "注册成功:");
                EventBus.getDefault().post(new FinishEvent());
                if (onShowAccount != null) {
                    onShowAccount.showAccount(user);
                }
                activity.replaceFragment(activity.getMainViewId(), LoginFragment.getInstance(), "LoginFragment");
            }

            @Override
            public void onFail(BmobException e) {
                LogUtil.d("Error:    code:" + e.getErrorCode() + "message:" + e.getMessage());
                CommonUtil.showLong("注册失败:" + e.getMessage());
            }
        });

    }

    private OnShowAccount onShowAccount;

    public void setOnShowAccount(OnShowAccount onShowAccount) {
        this.onShowAccount = onShowAccount;
    }

    public interface OnShowAccount {
        public void showAccount(JerryUser account);
    }
}
