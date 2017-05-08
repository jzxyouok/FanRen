package com.fanren.www.event;

/**
 * Author:      JerryChow
 * Date:        2017/3/30 14:44
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class FinishEvent {
    private String msg;
    public FinishEvent(){}

    public FinishEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
