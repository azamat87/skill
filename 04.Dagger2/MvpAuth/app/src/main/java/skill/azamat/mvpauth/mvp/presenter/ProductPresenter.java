package skill.azamat.mvpauth.mvp.presenter;

import javax.inject.Inject;

import dagger.Provides;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.ProductScope;
import skill.azamat.mvpauth.mvp.models.ProductModel;
import skill.azamat.mvpauth.mvp.view.IProductView;

/**
 * Created by Asus on 25.03.2018.
 */

public class ProductPresenter extends AbstractPresenter<IProductView> implements IProductPresenter {

    private static final String TAG = "ProductPresenter";

    @Inject
    ProductModel mProductModel;

    private ProductDto mProductDto;

    public ProductPresenter(ProductDto product) {

        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent(product);
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);
        mProductDto = product;
    }

    @Override
    public void initView() {
        if (getView() != null) {
            getView().showProductView(mProductDto);
        }
    }

    @Override
    public void clickOnPlus() {
        mProductDto.addProduct();
        mProductModel.updateProduct(mProductDto);
        if (getView() != null) {
            getView().updateProductCountView(mProductDto);
        }
    }

    @Override
    public void clickOnMinus() {
        if (mProductDto.getCount() > 0) {
            mProductDto.deleteProduct();
            mProductModel.updateProduct(mProductDto);
            if (getView() != null) {
                getView().updateProductCountView(mProductDto);
            }
        }
    }

//    region =============== DI =================================================================

    private Component createDaggerComponent(ProductDto product) {
        return DaggerProductPresenter_Component.builder()
                .module(new Module())
                .build();
    }

    @dagger.Module
    public class Module {

        @Provides
        @ProductScope
        ProductModel provideProductModel() {
            return new ProductModel();
        }

    }

    @dagger.Component(modules = Module.class)
    @ProductScope
    interface Component{
        void inject(ProductPresenter presenter);
    }

//    endregion
}
