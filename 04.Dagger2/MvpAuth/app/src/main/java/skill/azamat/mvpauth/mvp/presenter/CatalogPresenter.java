package skill.azamat.mvpauth.mvp.presenter;

import java.util.List;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.mvp.models.CatalogModel;
import skill.azamat.mvpauth.mvp.view.ICatalogView;

/**
 * Created by Asus on 31.03.2018.
 */

public class CatalogPresenter extends AbstractPresenter<ICatalogView> implements ICatalogPresenter {

    private static CatalogPresenter ourInstance = new CatalogPresenter();
    private CatalogModel mCatalogModel;
    private List<ProductDto> mProductDtoList;

    public static CatalogPresenter getInstance(){
        return ourInstance;
    }

    public CatalogPresenter() {
        mCatalogModel = new CatalogModel();
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
                getView().showAddToCardMessage(mProductDtoList.get(position));
            } else {
                getView().showAuthScreen();
            }
        }
    }

    @Override
    public boolean checkUserAuth() {
        return mCatalogModel.isUserAuth();
    }
}
