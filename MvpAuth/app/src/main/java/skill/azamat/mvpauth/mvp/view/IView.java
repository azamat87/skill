package skill.azamat.mvpauth.mvp.view;

/**
 * Created by Asus on 24.03.2018.
 */

public interface IView {

    void showMessage(String message);

    void showError(Throwable e);

    void showLoad();

    void hideLoad();

}
