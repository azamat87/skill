package skill.azamat.mvpauth.mvp.presenter;

/**
 * Created by Asus on 21.03.2018.
 */

public interface IAuthPresenter {

    void clickOnLogin();

    void clickOnFb();

    void clickOnVk();

    void clickOnTwitter();

    void clickOnShowCatalog();

    boolean checkUserAuth();
}
