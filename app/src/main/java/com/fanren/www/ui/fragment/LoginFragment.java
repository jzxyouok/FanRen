package com.fanren.www.ui.fragment;


import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanren.www.R;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.basic.UserManager;
import com.fanren.www.bean.JerryUser;
import com.fanren.www.event.FinishEvent;
import com.fanren.www.impl.OnRequestBmobListener;
import com.fanren.www.ui.activity.LoginActivity;
import com.fanren.www.util.CommonUtil;
import com.fanren.www.util.LogUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;

/**
 * Author:      JerryChow
 * Date:        2017/3/29 16:49
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class LoginFragment extends JerryFragment {
    @BindView(R.id.iv_logo_login)
    ImageView iv_logo_login;
    @BindView(R.id.ed_name_group_login)
    TextInputLayout ed_name_group;
    @BindView(R.id.ed_pwd_group_login)
    TextInputLayout ed_pwd_group;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.tv_forget_password)
    TextView tv_forget_password;
    @BindView(R.id.login_qq)
    ImageView login_qq;
    @BindView(R.id.login_weixin)
    ImageView login_weixin;
    TextInputEditText editName;
    TextInputEditText editPassword;
    private LoginActivity activity;
    private static LoginFragment loginFragment;

    public static LoginFragment getInstance() {
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }
        return loginFragment;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.layout_fragment_login;
    }

    @Override
    protected void initView(View view) {

        activity = (LoginActivity) getBindActivity();
        editName = (TextInputEditText) ed_name_group.getEditText();
        editPassword = (TextInputEditText) ed_pwd_group.getEditText();
//        RegisterFragment.getInstance().setOnShowAccount(new RegisterFragment.OnShowAccount() {
//            @Override
//            public void showAccount(JerryUser account) {
//                LogUtil.d("获取账号用户手机:" + account.getMobilePhoneNumber());
//                editName.setText(account.getMobilePhoneNumber());
//            }
//        });
    }

    @Override
    protected void doOperate() {

    }


    @OnClick({R.id.btn_login, R.id.btn_register, R.id.login_weixin, R.id.login_qq, R.id.tv_forget_password})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_register:
                activity.replaceFragment(activity.getMainViewId(), RegisterFragment.getInstance(), "RegisterFragment");
                break;
            case R.id.btn_login:
                logIn();
                break;
            case R.id.login_qq:
                break;
            case R.id.login_weixin:
                break;
            case R.id.tv_forget_password:
                activity.replaceFragment(activity.getMainViewId(), FindPassWordFragment.getInstance(), "FindPassWordFragment");
        }
    }

    //登录
    private void logIn() {
        String account = editName.getText().toString();
        String password = editPassword.getText().toString();
        UserManager.getInstance().loginByPhone(account, password, new OnRequestBmobListener<JerryUser>() {
            @Override
            public void onSuccess(JerryUser user) {

                if(user!=null){
                    CommonUtil.showShort("登陆成功");
                    //activity.startActivity(HomeActivity.class, null, true);
                    //BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar().getUrl()));
                }
            }

            @Override
            public void onFail(BmobException e) {
                LogUtil.d("Error:    code:" + e.getErrorCode() + ";msg:" + e.getMessage());
                CommonUtil.showShort("Error:    code:" + e.getErrorCode() + ";msg:" + e.getMessage());
            }
        });
    }
    @Subscribe
    public void onEventMainThread(FinishEvent event){
        //activity.finish();
    }
}
