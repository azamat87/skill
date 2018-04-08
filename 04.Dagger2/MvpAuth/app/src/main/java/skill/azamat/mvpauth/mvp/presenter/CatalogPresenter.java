package skill.azamat.mvpauth.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.CatalogScope;
import skill.azamat.mvpauth.mvp.models.CatalogModel;
import skill.azamat.mvpauth.mvp.view.ICatalogView;
import skill.azamat.mvpauth.mvp.view.IRootView;
import skill.azamat.mvpauth.ui.activities.RootActivity;

/**
 * Created by Asus on 31.03.2018.
 */

public class CatalogPresenter extends AbstractPresenter<ICatalogView> implements ICatalogPresenter {

//    @Inject
    RootPresenter mRootPresenter;

    @Inject
    CatalogModel mCatalogModel;

    private List<ProductDto> mProductDtoList;

    public CatalogPresenter() {
        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);
//        mCatalogModel = new CatalogModel();
    }

    @Override
    public void initView() {
        if (mProductDtoList == null) {
            mProductDtoList = mCatalogModel.getProductList();
        }
        if (getView() != null) {
            getView().showCatalogView(mProductDtoList);
        }
    }

    @Override
    public void clickOnBuyButton(int position) {
        if (getView() != null) {
            if (checkUserAuth()) {
                getRootView().showMessage("Товар " + mProductDtoList.get(position).getProductName() + " успешно добавлен в карзину");
            } else {
                getView().showAuthScreen();
            }
        }
    }

    public IRootView getRootView() {
        return mRootPresenter.getView();
    }

    @Override
    public boolean checkUserAuth() {
        return mCatalogModel.isUserAuth();
    }

//    region ================ DI ===================================================================

    private Component createDaggerComponent() {
        return DaggerCatalogPresenter_Component.builder()
                .component(DaggerService.getComponent(RootActivity.Component.class))
                .module(new Module())
                .build();
    }

    @dagger.Module
    public class Module {

        @Provides
        @CatalogScope
        CatalogModel provideProductModel() {
            return new CatalogModel();
        }
    }

    @dagger.Component(dependencies = RootActivity.Component.class, modules = Module.class)
    @CatalogScope
    interface Component{
        void inject(CatalogPresenter presenter);
    }

//    endregion
}
