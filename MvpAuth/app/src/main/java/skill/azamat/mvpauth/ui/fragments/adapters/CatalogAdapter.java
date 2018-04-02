package skill.azamat.mvpauth.ui.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.ui.fragments.ProductFragment;

/**
 * Created by Asus on 31.03.2018.
 */

public class CatalogAdapter extends FragmentPagerAdapter {

    private List<ProductDto> mListProduct = new ArrayList<>();

    public CatalogAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ProductFragment.newInstance(mListProduct.get(position));
    }

    @Override
    public int getCount() {
        return mListProduct.size();
    }

    public void addItem(ProductDto productDto) {
        mListProduct.add(productDto);
        notifyDataSetChanged();
    }
}
