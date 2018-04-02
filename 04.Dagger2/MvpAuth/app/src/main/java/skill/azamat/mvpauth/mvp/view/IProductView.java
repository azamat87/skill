package skill.azamat.mvpauth.mvp.view;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 24.03.2018.
 */

public interface IProductView extends IView {

    void showProductView(ProductDto productDto);

    void updateProductCountView(ProductDto product);

}
