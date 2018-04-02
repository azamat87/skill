package skill.azamat.mvpauth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import skill.azamat.mvpauth.R;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.mvp.presenter.ProductPresenter;
import skill.azamat.mvpauth.mvp.presenter.ProductPresenterFactory;
import skill.azamat.mvpauth.mvp.view.IProductView;
import skill.azamat.mvpauth.ui.activities.RootActivity;

/**
 * Created by Asus on 24.03.2018.
 */

public class ProductFragment extends Fragment implements IProductView, View.OnClickListener {

    private static final String TAG = "ProductFragment";

    @BindView(R.id.product_name_txt)
    TextView productNameTxt;
    @BindView(R.id.product_description_txt)
    TextView productDescriptionTxt;
    @BindView(R.id.product_image)
    ImageView productImge;
    @BindView(R.id.product_count_txt)
    TextView productCountTxt;
    @BindView(R.id.product_price_txt)
    TextView productPriceTxt;
    @BindView(R.id.plus_btn)
    ImageButton plusBtn;
    @BindView(R.id.minus_btn)
    ImageButton minusBtn;

//    @BindDrawable(R.id.product_image)

    private ProductPresenter mPresenter;

    public ProductFragment() {
    }

    public static ProductFragment newInstance(ProductDto productDto) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("PRODUCT", productDto);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            ProductDto product = bundle.getParcelable("PRODUCT");
            // TODO: 25.03.2018 init presenter
            mPresenter = ProductPresenterFactory.getInstance(product);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);
        readBundle(getArguments());
        mPresenter.takeView(this);
        mPresenter.initView();
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }

    // region ===================== IProductView ==================================================

    @Override
    public void showMessage(String message) {
        getRootActivity().showMessage(message);
    }

    @Override
    public void showError(Throwable e) {
        getRootActivity().showError(e);
    }

    @Override
    public void showLoad() {
        getRootActivity().showLoad();
    }

    @Override
    public void hideLoad() {
        getRootActivity().hideLoad();
    }

    @Override
    public void showProductView(ProductDto productDto) {
        productNameTxt.setText(productDto.getProductName());
        productDescriptionTxt.setText(productDto.getDescription());
        productCountTxt.setText(String.valueOf(productDto.getCount()));
        if (productDto.getCount() > 0) {
            productPriceTxt.setText(String.valueOf(productDto.getCount() * productDto.getPrice() + ".-"));
        } else {
            productPriceTxt.setText(String.valueOf(productDto.getPrice() + ".-"));
        }

        // TODO: 25.03.2018 Picasso image

    }

    @Override
    public void updateProductCountView(ProductDto product) {
        productCountTxt.setText(String.valueOf(product.getCount()));
        if (product.getCount() > 0) {
            productPriceTxt.setText(String.valueOf(product.getCount()*product.getPrice() + ".-"));
        }
    }

    // endregion

    private RootActivity getRootActivity() {
        return (RootActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_btn:
                mPresenter.clickOnPlus();
                break;
            case R.id.minus_btn:
                mPresenter.clickOnMinus();
                break;
        }
    }
}
