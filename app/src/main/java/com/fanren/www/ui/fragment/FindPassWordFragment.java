package com.fanren.www.ui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fanren.www.R;
import com.fanren.www.basic.JerryFragment;
import com.fanren.www.ui.activity.LoginActivity;
import com.fanren.www.ui.customview.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:      JerryChow
 * Date:        2017/3/30 15:25
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:     找回密码界面
 */
public class FindPassWordFragment extends JerryFragment {
    @BindView(R.id.ed_phone_nub)
    EditText ed_new_pwd;
    @BindView(R.id.ed_confirm_pwd)
    EditText ed_confirm_pwd;
    @BindView(R.id.btn_reset_password)
    TextView btn_reset_password;

    TextView title;
    @BindView(R.id.tv_get_code)
    TextView tv_get_code;
    @BindView(R.id.titleBar_findPwd)
    TitleBar titleBar;
    private String phoneNum,phoneCode;
    private LoginActivity activity;
    private static FindPassWordFragment loginFragment;
    public static FindPassWordFragment getInstance(){
        if(loginFragment==null){
            loginFragment=new FindPassWordFragment();
        }
        return loginFragment;
    }
    @Override
    protected int inflateLayout() {
        return R.layout.layout_fragment_findpwd;
    }

    @Override
    protected void initView(View view) {
        activity= (LoginActivity) getBindActivity();
    }

    @Override
    protected void doOperate() {

    }

    @OnClick({ R.id.ed_phone_nub, R.id.ed_confirm_pwd, R.id.btn_reset_password,R.id.tv_get_code})

    public void onClick(View v) {
        int id=v.getId();
        switch(id){
//            case R.id.back:
//                activity.replaceFragment(activity.getMainViewId(),LoginFragment.getInstance(),"LoginFragment");
//                break;
            case R.id.tv_get_code:
                //getPhoneCode();
                break;
            case R.id.ed_phone_nub:
                phoneNum=ed_new_pwd.getText().toString();
                break;
            case R.id.ed_confirm_pwd:
                phoneCode=ed_confirm_pwd.getText().toString();
                break;
            case R.id.btn_reset_password:
                //resetPassword(phoneNum,phoneCode);
                break;
        }
    }
    //获取手机验证码
//    private void getPhoneCode() {
//        UserManager.getInstance().sendPhoneCode(phoneNum, Constant.PHONE_CODE_PALATE, new OnRequestBmobListener() {
//            @Override
//            public void onSuccess(Object o) {
//                CommonUtil.d("获取短信验证码成功:"+o.toString());
//            }
//
//            @Override
//            public void onFail(BmobException e) {
//                CommonUtil.d("获取短信验证码失败:"+e.getErrorCode()+"msg:"+e.getMessage());
//            }
//        });
//    }
    //根据手机验证码重置密码
//    private void resetPassword(String newPassword,String phoneCode) {
//        UserManager.getInstance().resetPassword(phoneCode, newPassword, new OnRequestBmobListener() {
//            @Override
//            public void onSuccess(Object o) {
//                CommonUtil.d("重置密码成功！");
//                CommonUtil.showLong("重置密码成功！");
//            }
//
//            @Override
//            public void onFail(BmobException e) {
//                CommonUtil.d("重置密码失败:"+e.getMessage()+":"+e.getErrorCode());
//            }
//        });
//    }
}
