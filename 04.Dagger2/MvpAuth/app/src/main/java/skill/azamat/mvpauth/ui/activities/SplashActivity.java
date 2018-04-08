package skill.azamat.mvpauth.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import skill.azamat.mvpauth.BuildConfig;
import skill.azamat.mvpauth.R;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.AuthScope;
import skill.azamat.mvpauth.mvp.presenter.AuthPresenter;
import skill.azamat.mvpauth.mvp.presenter.IAuthPresenter;
import skill.azamat.mvpauth.mvp.view.IAuthView;
import skill.azamat.mvpauth.ui.custom_views.AuthPanel;

public class SplashActivity extends AppCompatActivity implements IAuthView, View.OnClickListener{

    @Inject
    AuthPresenter mPresenter;

    @BindView(R.id.coordinator_container)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.auth_wrapper)
    AuthPanel mAuthPanel;

    @BindView(R.id.show_catalog_btn)
    Button mShowCatalogBtn;

    @BindView(R.id.login_btn)
    Button mLoginBtn;


//region  ================== Life cycle ===========================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);

        mPresenter.takeView(this);
        mPresenter.initView();

        mLoginBtn.setOnClickListener(this);
        mShowCatalogBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.dropView();
        if (isFinishing()) {
            DaggerService.unregisterScope(AuthScope.class);
        }

        super.onDestroy();
    }

    // endregion


//    region =============== IAuthView ==================================================

    @Override
    public void showMessage(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable e) {
        if (BuildConfig.DEBUG) {
            showMessage(e.getMessage());
            e.printStackTrace();
        } else {
            showMessage("Извените что то не пошло такб, побробуйте позже");
            // TODO: 21.03.2018 send error to crashlitics
        }
    }

    @Override
    public void showLoad() {
        // TODO: 21.03.2018 progress dialog
    }

    @Override
    public void hideLoad() {
        // TODO: 21.03.2018 progress dialog
    }

    @Override
    public IAuthPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showLoginBtn() {
        mLoginBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginBtn() {
        mLoginBtn.setVisibility(View.GONE);
    }

//    @Override
//    public void testShowLoginCard() {
//        mAuthCard.setVisibility(View.GONE);
//    }

    @Override
    public AuthPanel getAuthPanel() {
        return mAuthPanel;
    }

    @Override
    public void showCatalogScreen() {
        Intent intent = new Intent(this, RootActivity.class);
        startActivity(intent);
        finish();
    }

    // endregion


    @Override
    public void onBackPressed() {
        if (getAuthPanel() != null)
            if (!getAuthPanel().isIdle()) {
                mAuthPanel.setCustomState(AuthPanel.IDLE_STATE);
            } else {
                super.onBackPressed();
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mPresenter.clickOnLogin();
                break;
            case R.id.show_catalog_btn:
                mPresenter.clickOnShowCatalog();
                break;
        }
    }


    // region =============== DI ==================================================================

    @dagger.Module
    public class Module {

        @Provides
        @AuthScope
        AuthPresenter provideAuthPresenter() {
            return new AuthPresenter();
        }
    }

    @dagger.Component(modules = Module.class)
    @AuthScope
    interface Component{
        void inject(SplashActivity activity);
    }

    private Component createDaggerComponent() {
        return DaggerSplashActivity_Component.builder()
                .module(new Module())
                .build();
    }



    // endregion
}
