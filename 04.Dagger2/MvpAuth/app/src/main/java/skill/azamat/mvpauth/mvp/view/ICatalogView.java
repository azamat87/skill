package skill.azamat.mvpauth.mvp.view;

import java.util.List;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 31.03.2018.
 */

public interface ICatalogView extends IView {

    void showAddToCardMessage(ProductDto productDto);

    void showCatalogView(List<ProductDto> productsDtos);

    void showAuthScreen();

    void updateProductCounter();
}
