package skill.azamat.mvpauth.mvp.view;

/**
 * Created by Asus on 08.04.2018.
 */

public interface IRootView extends IView{

    void showMessage(String message);

    void showError(Throwable e);

    void showLoad();

    void hideLoad();

}
