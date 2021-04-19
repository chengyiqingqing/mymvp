package com.sww.basemvp.presenter;

import android.content.Context;

import androidx.annotation.CallSuper;

import com.sww.basemvp.view.IMvpView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Presenter的抽象父类，完成通用逻辑
 * @author ShaoWenWen
 * @date 2019-08-15
 */
public abstract class AbsMvpPresenter<V extends IMvpView> {

    private V mMvpView;
    public V mView;

    @SuppressWarnings("unchecked")
    @CallSuper
    public void attach(V view) {
        this.mMvpView = view;
        this.mView = (V) Proxy.newProxyInstance(mMvpView.getClass().getClassLoader(), mMvpView.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (!checkContextEnable()) return null;
                return method.invoke(mMvpView, args);
            }
        });
    }

    public Context getContext() {
        if (mMvpView == null) return null;
        return mMvpView.getContext();
    }

    public boolean checkContextEnable() {
        return mMvpView != null && mMvpView.checkContextEnable();
    }

    @CallSuper
    public void detach() {
        mMvpView = null;
    }

}

