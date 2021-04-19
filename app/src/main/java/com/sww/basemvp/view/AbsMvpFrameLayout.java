package com.sww.basemvp.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sww.basemvp.presenter.AbsMvpPresenter;
import com.sww.basemvp.presenter.IMvpPresenter;
import com.sww.basemvp.utils.ContextUtils;
import com.sww.basemvp.utils.InstanceUtils;

/**
 * Mvp通用FrameLayout 【为广告Sdk5.0的广告组件分层】也可用于其它带有稍复杂业务场景的ViewGroup
 * @author ShaoWenWen
 * @date 2019-08-15
 */
public class AbsMvpFrameLayout<T extends AbsMvpPresenter & IMvpPresenter, U extends IMvpPresenter> extends FrameLayout implements IMvpView<U> {

    protected U mPresenter;

    public AbsMvpFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public AbsMvpFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (mPresenter != null) return;
        T presenterInstance = InstanceUtils.getInstance(this);
        initPresenter(presenterInstance);
    }

    @SuppressWarnings("unchecked")
    protected void initPresenter(Object presenterInstance) {
        if (presenterInstance == null) {
            throw new RuntimeException("presenter is null");
        } else if (!(presenterInstance instanceof AbsMvpPresenter)) {
            throw new RuntimeException("presenter 实例需要实现 AbsMvpPresenter");
        } else if (!(presenterInstance instanceof IMvpPresenter)) {
            throw new RuntimeException("presenter 实例需要实现 IMvpPresenter");
        }
        this.mPresenter = (U) presenterInstance;
        ((T) presenterInstance).attach(this);
    }

    @Override
    public boolean checkContextEnable() {
        return ContextUtils.isActivityValid(getContext());
    }

    @Override
    public void finishActivity() {
        Activity activity = ContextUtils.findActivity(getContext());
        if (activity != null) {
            activity.finish();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null) ((T) mPresenter).detach();
    }

}
