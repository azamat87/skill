package skill.azamat.mvpauth.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import skill.azamat.mvpauth.BuildConfig;
import skill.azamat.mvpauth.R;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.RootScope;
import skill.azamat.mvpauth.mvp.presenter.RootPresenter;
import skill.azamat.mvpauth.mvp.view.IRootView;
import skill.azamat.mvpauth.ui.fragments.AccountFragment;
import skill.azamat.mvpauth.ui.fragments.CatalogFragment;

public class RootActivity extends AppCompatActivity implements IRootView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavView;
    @BindView(R.id.root_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.coordinator_container)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    FragmentManager mFragmentManager;

    @Inject
    RootPresenter mRootPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        ButterKnife.bind(this);

        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);

        initToolbar();
        initDrawer();
        mRootPresenter.takeView(this);
        mRootPresenter.initView();

        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new CatalogFragment())
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        mRootPresenter.dropView();
        super.onDestroy();
    }

    private void initToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);

    }

    private void initDrawer() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_account:
                fragment = new AccountFragment();
                break;
            case R.id.nav_catalog:
                fragment = new CatalogFragment();
                break;
            case R.id.nav_favorite:

                break;
            case R.id.nav_orders:

                break;
            case R.id.nav_notification:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        if (fragment != null) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        return false;
    }

    // region ============= IRootView =================================================================

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
            showMessage(getString(R.string.error_message));
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

    // endregion


    //    region ================ DI ===================================================================

    private Component createDaggerComponent() {
        return DaggerRootActivity_Component.builder()
                .module(new Module())
                .build();
    }

    @dagger.Module
    public class Module {

        @Provides
        @RootScope
        RootPresenter provideRootPresenter() {
            return new RootPresenter();
        }

    }

    @dagger.Component(modules = Module.class)
    @RootScope
    public interface Component{
        void inject(RootActivity activity);
        RootPresenter getRootPresenter();

    }

//    endregion
}
