package skill.azamat.mvpauth.data.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import skill.azamat.mvpauth.App;
import skill.azamat.mvpauth.data.network.RestService;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.components.DaggerDataManagerComponent;
import skill.azamat.mvpauth.di.components.DataManagerComponent;
import skill.azamat.mvpauth.di.modules.LocalModule;
import skill.azamat.mvpauth.di.modules.NetworkModule;

/**
 * Created by Asus on 24.03.2018.
 */

public class DataManager {

    private List<ProductDto> mMockProductList;

    @Inject
    PreferencesManager mPreferencesManager;
    @Inject
    RestService mRestService;

    public DataManager() {

        DataManagerComponent component = DaggerService.getComponent(DataManagerComponent.class);
        if (component == null) {
            component = DaggerDataManagerComponent.builder()
                    .appComponent(App.getAppComponent())
                    .localModule(new LocalModule())
                    .networkModule(new NetworkModule())
                    .build();
            DaggerService.registerComponent(DataManagerComponent.class, component);
        }
        component.inject(this);

        generateMockData();
    }

    public ProductDto getProductById(int productId) {
        // TODO: 24.03.2018
        return mMockProductList.get(productId+1);
    }

    public void updateProduct(ProductDto productDto) {
        // TODO: 24.03.2018 updte data
    }

    public List<ProductDto> getProductList() {
        return mMockProductList;
    }

    private void generateMockData() {
        mMockProductList = new ArrayList<>();
        mMockProductList.add(new ProductDto(1, "Product 1", "imageUrl", "description 1 description 1 description 1 description 1", 100, 1));
        mMockProductList.add(new ProductDto(2, "Product 2", "imageUrl", "description 1 description 1 description 1 description 1", 200, 1));
        mMockProductList.add(new ProductDto(3, "Product 3", "imageUrl", "description 1 description 1 description 1 description 1", 300, 1));
        mMockProductList.add(new ProductDto(4, "Product 4", "imageUrl", "description 1 description 1 description 1 description 1", 400, 1));
        mMockProductList.add(new ProductDto(5, "Product 5", "imageUrl", "description 1 description 1 description 1 description 1", 500, 1));
        mMockProductList.add(new ProductDto(6, "Product 6", "imageUrl", "description 1 description 1 description 1 description 1", 600, 1));
        mMockProductList.add(new ProductDto(7, "Product 7", "imageUrl", "description 1 description 1 description 1 description 1", 700, 1));
        mMockProductList.add(new ProductDto(8, "Product 8", "imageUrl", "description 1 description 1 description 1 description 1", 800, 1));
    }

    public boolean isAuthUser() {
        // TODO: 31.03.2018 check user auth token
        return false;
    }
}
