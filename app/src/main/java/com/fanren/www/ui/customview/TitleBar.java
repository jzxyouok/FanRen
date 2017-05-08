package com.fanren.www.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fanren.www.R;


/**
 * Author:      JerryChow
 * Date:        2017/4/8 10:57
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 公用titleBar
 */
public class TitleBar extends FrameLayout implements
        View.OnClickListener {

    private FrameLayout mLeft;
    private FrameLayout mRight;
    private LinearLayout mMiddle;

    private OnTitleBarClickListener mOnClickListener;

    public TitleBar(Context context) {
        super(context);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.vw_generic_title,
                this);
        mLeft = (FrameLayout) findViewById(R.id.fl_title_left);
        mRight = (FrameLayout) findViewById(R.id.fl_title_right);
        mMiddle = (LinearLayout) findViewById(R.id.fl_title_middle);

    }

    public void setOnClickListener(OnTitleBarClickListener listener) {
        mOnClickListener = listener;
    }

    public void setLeft(View view) {
        setLeft(view, Gravity.LEFT | Gravity.CENTER_VERTICAL);
    }

    /**
     *
     * @param view
     * @param gravity
     *            Gravity 中的属性
     */
    public void setLeft(View view, int gravity) {
        if (null != view) {
            mLeft.removeAllViews();
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            lp.gravity = gravity;
            view.setLayoutParams(lp);
            mLeft.setOnClickListener(this);
            mLeft.addView(view);
            mLeft.setTag(true);
        }
    }

    public void setMiddle(View view) {
        if (view != null) {
            mMiddle.removeAllViews();
            mMiddle.setOnClickListener(this);
            mMiddle.addView(view);
            mMiddle.setTag(true);
        }
    }

    public void setRight(View view) {
        setRight(view, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    }

    /**
     *
     * @param view
     * @param grivaty
     *            Gravity 中的属性
     */
    public void setRight(View view, int grivaty) {
        if (null != view) {
            mRight.removeAllViews();
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            lp.gravity = grivaty;
            view.setLayoutParams(lp);
            mRight.setOnClickListener(this);
            mRight.addView(view);
            mRight.setTag(true);
        }
    }

    public  interface OnTitleBarClickListener {

        void onClickLeft();

        void onClickMiddle();

        void onClickRight();
    }

    @Override
    public void onClick(View v) {
        if (null != mOnClickListener) {
            final boolean isValid = (Boolean) v.getTag();
            if (isValid) {
                if (v == mLeft) {
                    mOnClickListener.onClickLeft();
                } else if (v == mRight) {
                    mOnClickListener.onClickRight();
                } else if (v == mMiddle) {
                    mOnClickListener.onClickMiddle();
                }
            }
        }
    }
}
