package com.fanren.www.basic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Author:      JerryChow
 * Date:        2017/4/6 11:50
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public abstract class JerryAdapter<T> extends BaseAdapter {
    private List<T> data;
    private Context context;
    private LayoutInflater inflater;
    public JerryAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
        inflater= LayoutInflater.from(context);
    }
    public void addAll(List<T> data){
        this.data.addAll(data);
    }
    public void setData(List<T> data){
        this.data.clear();
        this.data.addAll(data);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.getInstance(context, inflateItemLayout(), position, convertView, parent);
        convert(holder,data.get(position));
        return holder.getConvertView();
    }
    /**
     * 填充holder里面控件的数据
     * @param holder
     * @param bean
     */
    public abstract void convert(CommonViewHolder holder,T bean);
    //获取item布局id
    public abstract int inflateItemLayout();
}
