package skill.azamat.mvpauth.mvp.presenter;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.mvp.models.ProductModel;
import skill.azamat.mvpauth.mvp.view.IProductView;

/**
 * Created by Asus on 25.03.2018.
 */

public class ProductPresenter extends AbstractPresenter<IProductView> implements IProductPresenter {

    private static final String TAG = "ProductPresenter";

    private ProductModel mProductModel;
    private ProductDto mProductDto;

    public static ProductPresenter newInstance(ProductDto product) {

        return new ProductPresenter(product);
    }

    private ProductPresenter(ProductDto product) {
        mProductModel = new ProductModel();
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
}
