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

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import skill.azamat.mvpauth.App;
import skill.azamat.mvpauth.R;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.components.DaggerPicassoComponent;
import skill.azamat.mvpauth.di.components.PicassoComponent;
import skill.azamat.mvpauth.di.modules.PicassoCacheModule;
import skill.azamat.mvpauth.di.scopes.ProductScope;
import skill.azamat.mvpauth.mvp.presenter.ProductPresenter;
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
    @Inject
    ProductPresenter mPresenter;

    @Inject
    Picasso mPicasso;

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
            Component component = createDaggerComponent(product);
            component.inject(this);
            // TODO: 08.04.2018 fix recreate
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

//    @Override
//    public void showMessage(String message) {
//        getRootActivity().showMessage(message);
//    }
//
//    @Override
//    public void showError(Throwable e) {
//        getRootActivity().showError(e);
//    }
//
//    @Override
//    public void showLoad() {
//        getRootActivity().showLoad();
//    }
//
//    @Override
//    public void hideLoad() {
//        getRootActivity().hideLoad();
//    }

    @Override
    public void showProductView(final ProductDto productDto) {
        productNameTxt.setText(productDto.getProductName());
        productDescriptionTxt.setText(productDto.getDescription());
        productCountTxt.setText(String.valueOf(productDto.getCount()));
        if (productDto.getCount() > 0) {
            productPriceTxt.setText(String.valueOf(productDto.getCount() * productDto.getPrice() + ".-"));
        } else {
            productPriceTxt.setText(String.valueOf(productDto.getPrice() + ".-"));
        }
        mPicasso.load(productDto.getImageUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .centerCrop()
                .into(productImge, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        mPicasso.load(productDto.getImageUrl())
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .fit()
                                .centerCrop()
                                .into(productImge);
                    }
                });

    }

    @Override
    public void updateProductCountView(ProductDto product) {
        productCountTxt.setText(String.valueOf(product.getCount()));
        if (product.getCount() > 0) {
            productPriceTxt.setText(String.valueOf(product.getCount() * product.getPrice() + ".-"));
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

//    region =================== DI ==============================================================

    private Component createDaggerComponent(ProductDto product) {
        PicassoComponent picassoComponent = DaggerService.getComponent(PicassoComponent.class);
        if (picassoComponent == null) {
            picassoComponent = DaggerPicassoComponent.builder()
                    .appComponent(App.getAppComponent())
                    .picassoCacheModule(new PicassoCacheModule())
                    .build();

            DaggerService.registerComponent(PicassoComponent.class, picassoComponent);
        }

        return DaggerProductFragment_Component.builder()
                .picassoComponent(picassoComponent)
                .module(new Module(product))
                .build();
    }

    @dagger.Module
    public class Module {

        ProductDto mProductDto;

        public Module(ProductDto productDto) {
            mProductDto = productDto;
        }

        @Provides
        @ProductScope
        ProductPresenter provideProductPresenter() {
            return new ProductPresenter(mProductDto);
        }

    }

    @dagger.Component(dependencies = PicassoComponent.class,modules = Module.class)
    @ProductScope
    interface Component{
        void inject(ProductFragment fragment);
    }

//    endregion
}
