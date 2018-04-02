package skill.azamat.mvpauth.data;

import java.util.ArrayList;
import java.util.List;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;

/**
 * Created by Asus on 24.03.2018.
 */

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private List<ProductDto> mMockProductList;

    private DataManager() {
        generateMockData();
    }

    public static DataManager getInstance() {
        return ourInstance;
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
