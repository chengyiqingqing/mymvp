package com.sww.basemvp.view;

import android.content.Context;

import com.sww.basemvp.presenter.IMvpPresenter;


/**
 * mvp中View接口类
 * @author ShaoWenWen
 * @date 2019-08-15
 */
public interface IMvpView<T extends IMvpPresenter> {

    /**
     * 获取当前View的上下文
     */
    Context getContext();

    /**
     * 检查当前view所在的上下文是否可用
     */
    boolean checkContextEnable();

    /**
     * 关闭当前Activity
     */
    void finishActivity();

}
