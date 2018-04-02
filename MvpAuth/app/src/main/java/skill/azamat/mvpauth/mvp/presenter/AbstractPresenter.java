package skill.azamat.mvpauth.mvp.presenter;

import android.support.annotation.Nullable;

import skill.azamat.mvpauth.mvp.view.IView;

/**
 * Created by Asus on 24.03.2018.
 */

public abstract class AbstractPresenter<T extends IView> {

    private T mView;

    public void takeView(T view) {
        mView = view;
    }

    public void dropView() {
        mView = null;
    }

    public abstract void initView();

    @Nullable
    public T getView() {
        return mView;
    }
}
