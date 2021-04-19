package com.sww.basemvp.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.sww.basemvp.presenter.AbsMvpPresenter;
import com.sww.basemvp.presenter.IMvpPresenter;
import com.sww.basemvp.utils.InstanceUtils;

/**
 * Mvp通用Fragment
 * @author ShaoWenWen
 * @date 2019-08-15
 */
public abstract class AbsMvpFragment<T extends AbsMvpPresenter & IMvpPresenter, U extends IMvpPresenter> extends Fragment implements IMvpView<U> {

    protected U mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) return;
        T presenterInstance = InstanceUtils.getInstance(this);
        initPresenter(presenterInstance);
    }

    @SuppressWarnings("unchecked")
    protected void initPresenter(Object presenterInstance) {
        if (presenterInstance == null) {
            throw new RuntimeException("presenter is null");
        } else if (!(presenterInstance instanceof AbsMvpPresenter)) {
            throw new RuntimeException("presenter 实例需要继承 AbsMvpPresenter");
        } else if (!(presenterInstance instanceof IMvpPresenter)) {
            throw new RuntimeException("presenter 实例需要实现 IMvpPresenter");
        }
        this.mPresenter = (U) presenterInstance;
        ((T) presenterInstance).attach(this);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public boolean checkContextEnable() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || isDetached()) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return !activity.isDestroyed();
        return true;
    }

    @Override
    public void finishActivity() {
        if (getContext() != null && getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) ((T) mPresenter).detach();
    }

}
