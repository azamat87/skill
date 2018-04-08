package skill.azamat.mvpauth.mvp.models;

import skill.azamat.mvpauth.data.managers.DataManager;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 24.03.2018.
 */

public class ProductModel extends AbstractModel {

    DataManager mDataManager;

    public ProductDto getProductById(int productId) {
        // TODO: 24.03.2018 get product from manager
        return mDataManager.getProductById(productId);
    }

    public void updateProduct(ProductDto productDto) {
        mDataManager.updateProduct(productDto);
    }
}
