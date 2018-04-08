package skill.azamat.mvpauth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import skill.azamat.mvpauth.R;
import skill.azamat.mvpauth.data.storage.dto.ProductDto;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.scopes.CatalogScope;
import skill.azamat.mvpauth.mvp.presenter.CatalogPresenter;
import skill.azamat.mvpauth.mvp.view.ICatalogView;
import skill.azamat.mvpauth.ui.fragments.adapters.CatalogAdapter;

/**
 * Created by Asus on 31.03.2018.
 */

public class CatalogFragment extends Fragment implements ICatalogView, View.OnClickListener {


    @Inject
    CatalogPresenter mPresenter;

    @BindView(R.id.add_to_card_btn)
    Button addToCardBtn;

    @BindView(R.id.product_pager)
    ViewPager productPager;

    public CatalogFragment() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_to_card_btn) {
            mPresenter.clickOnBuyButton(productPager.getCurrentItem());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, view);

        Component component = DaggerService.getComponent(Component.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(Component.class, component);
        }
        component.inject(this);

        mPresenter.takeView(this);
        mPresenter.initView();
        addToCardBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }

    //region ================== ICatalogView =====================================================


    @Override
    public void showCatalogView(List<ProductDto> productsDtos) {
        CatalogAdapter adapter = new CatalogAdapter(getChildFragmentManager());
        for (ProductDto product : productsDtos) {
            adapter.addItem(product);
        }
        productPager.setAdapter(adapter);
    }

    @Override
    public void showAuthScreen() {
        // TODO: 31.03.2018 show auth screen if user not auth
    }

    @Override
    public void updateProductCounter() {
        // TODO: 31.03.2018 update count product

    }

    // end region


    // region ========================= IView ====================================================

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

    // endregion



//    region ============ DI ====================================================================

    private Component createDaggerComponent() {

        return DaggerCatalogFragment_Component.builder()
                .module(new Module())
                .build();
    }

    @dagger.Module
    public class Module {

        @Provides
        @CatalogScope
        CatalogPresenter provideCatalogPresenter() {
            return new CatalogPresenter();
        }

    }

    @dagger.Component(modules = Module.class)
    @CatalogScope
    interface Component{
        void inject(CatalogFragment fragment);
    }

//    endregion
}
