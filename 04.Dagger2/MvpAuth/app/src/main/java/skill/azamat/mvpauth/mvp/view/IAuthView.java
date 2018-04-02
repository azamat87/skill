package skill.azamat.mvpauth.mvp.view;

import android.support.annotation.Nullable;

import skill.azamat.mvpauth.mvp.presenter.IAuthPresenter;
import skill.azamat.mvpauth.ui.custom_views.AuthPanel;

/**
 * Created by Asus on 21.03.2018.
 */

public interface IAuthView extends IView{

    IAuthPresenter getPresenter();

    void showLoginBtn();

    void hideLoginBtn();

//    void testShowLoginCard();
    @Nullable
    AuthPanel getAuthPanel();

    void showCatalogScreen();
}
