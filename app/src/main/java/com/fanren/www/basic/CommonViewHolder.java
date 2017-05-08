package com.fanren.www.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author:      JerryChow
 * Date:        2017/4/6 14:50
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class CommonViewHolder {
    private int mPosition;
    /*
     * 用于存储holder里面的各个view，此集合比map效率高,但key必须为Integer
     */
    private SparseArray<View> mViews;
    /**
     * 复用的view
     */
    private View convertView;

    private CommonViewHolder(Context context, int position, int layoutId, ViewGroup parent) {
        this.mPosition = position;
        mViews = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        convertView.setTag(this);
    }

    public static CommonViewHolder getInstance(Context context,int layoutId,int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            return new CommonViewHolder(context, position, layoutId, parent);
        } else {
            CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }

    }

    /**
     * 通过resourceId获取item里面的view
     * @param resourceId 控件的id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int resourceId) {
        View view = mViews.get(resourceId);
        if (view == null) {
            view = convertView.findViewById(resourceId);
            mViews.put(resourceId, view);
        }
        return (T) view;
    }

    /**
     * 为textview类型填充内容
     * @param resourceId
     * @param text
     * @return CommonViewHolder
     */
    public CommonViewHolder setText(int resourceId,CharSequence text ) {
        ((TextView) getView(resourceId)).setText(text);
        return this;
    }
    public CommonViewHolder setText(int resourceId,int resid ) {
        ((TextView) getView(resourceId)).setText(resid);
        return this;
    }
    /**
     * 为ImageView设置Bitmap
     * @param resourceId
     * @param bm
     * @return
     */
    public CommonViewHolder setBitmap(int resourceId,Bitmap bm) {
        ((ImageView)getView(resourceId)).setImageBitmap(bm);
        return  this;
    }
    public CommonViewHolder setImageDrawable(int resourceId,Drawable drawable) {
        ((ImageView)getView(resourceId)).setImageDrawable(drawable);
        return  this;
    }
    public CommonViewHolder setImageResource(int resourceId,int resId) {
        ((ImageView)getView(resourceId)).setImageResource(resId);
        return  this;
    }

    public ImageView getImageView(int id){
        return (ImageView) convertView.findViewById(id);
    }
    public View getConvertView() {
        return convertView;
    }
    /**
     * 获取当前item的位置
     * @return
     */
    public int getPosition() {
        return mPosition;
    }
}
