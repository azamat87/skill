package skill.azamat.mvpauth.mvp.presenter;

import javax.inject.Inject;

import dagger.Provides;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.AuthScope;
import skill.azamat.mvpauth.mvp.models.AuthModel;
import skill.azamat.mvpauth.mvp.view.IAuthView;
import skill.azamat.mvpauth.ui.custom_views.AuthPanel;

/**
 * Created by Asus on 21.03.2018.
 */

public class AuthPresenter extends AbstractPresenter<IAuthView> implements IAuthPresenter {

    @Inject
    AuthModel mAuthModel;

    public AuthPresenter() {
        mAuthModel = new AuthModel();


        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);

    }

    @Override
    public void initView() {
        if (getView() != null) {
            if (checkUserAuth()) {
                getView().hideLoginBtn();
            } else {
                getView().showLoginBtn();
            }
        }
    }

    @Override
    public void clickOnLogin() {
        // TODO: 21.03.2018 show and hide login panel
        if (getView() != null && getView().getAuthPanel() != null) {
            if (getView().getAuthPanel().isIdle()) {
                getView().getAuthPanel().setCustomState(AuthPanel.LOGIN_STATE);
            } else {
                // TODO: 21.03.2018 auth user
                mAuthModel.loginUser(getView().getAuthPanel().getUserEmail(), getView().getAuthPanel().getUserPassword());
                getView().showMessage("request auth");
            }
        }
    }

    @Override
    public void clickOnFb() {
        if (getView() != null) {
            getView().showMessage("clickOnFb");
        }
    }

    @Override
    public void clickOnVk() {
        if (getView() != null) {
            getView().showMessage("clickOnVk");
        }
    }

    @Override
    public void clickOnTwitter() {
        if (getView() != null) {
            getView().showMessage("clickOnVk");
        }
    }

    @Override
    public void clickOnShowCatalog() {
        if (getView() != null) {
            getView().showMessage("Показать каталог");
            // TODO: 23.03.2018 update catlog
            getView().showCatalogScreen();
        }
    }

    @Override
    public boolean checkUserAuth() {
        return mAuthModel.isAuthUser();
    }


    // region ==================== DI =============================================================

    @dagger.Module
    public class Module {
        @Provides
        @AuthScope
        AuthModel provideAuthModule() {
            return new AuthModel();
        }
    }

    @dagger.Component(modules = Module.class)
    @AuthScope
    interface Component {
        void inject(AuthPresenter presenter);
    }

    private Component createDaggerComponent() {
        return DaggerAuthPresenter_Component.builder()
                .module(new Module())
                .build();
    }

    // endregion

}
