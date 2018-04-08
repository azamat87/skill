package skill.azamat.mvpauth.mvp.models;

import java.util.List;

import skill.azamat.mvpauth.data.managers.DataManager;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 31.03.2018.
 */

public class CatalogModel extends AbstractModel {

    DataManager mDataManager;

    public CatalogModel() {
    }

    public List<ProductDto> getProductList() {
        return mDataManager.getProductList();
    }

    public boolean isUserAuth() {
        return mDataManager.isAuthUser();
    }

}
