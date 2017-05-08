package com.fanren.www.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class JerryFragment extends Fragment {
    private Unbinder unbinder;
    protected abstract int inflateLayout();

    protected abstract void initView(View view);

    protected abstract void doOperate();
    private JerryActivity bindActivity;
    protected final String TAG=this.getClass().getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(inflateLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        initView(view);

    }
    protected FragmentTransaction getTranscation(){
        return bindActivity.getSupportFragmentManager().beginTransaction();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bindActivity= (JerryActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doOperate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    protected JerryActivity getBindActivity(){
        return bindActivity;
    }
}
