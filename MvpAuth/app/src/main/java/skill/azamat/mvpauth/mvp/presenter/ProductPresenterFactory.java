package skill.azamat.mvpauth.mvp.presenter;

import java.util.HashMap;
import java.util.Map;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 26.03.2018.
 */

public class ProductPresenterFactory {

    private static final Map<String, ProductPresenter> sPresenterMap = new HashMap<String, ProductPresenter>();

    private static void registerPresenter(ProductDto productDto, ProductPresenter presenter) {
        sPresenterMap.put(String.valueOf(productDto.getId()), presenter);
    }

    public static ProductPresenter getInstance(ProductDto productDto) {
        ProductPresenter presenter = sPresenterMap.get(String.valueOf(productDto.getId()));
        if (presenter == null) {
            presenter = ProductPresenter.newInstance(productDto);
            registerPresenter(productDto, presenter);
        }
        return presenter;
    }

}
