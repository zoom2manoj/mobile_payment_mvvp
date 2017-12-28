package com.manoj.mobilepayment.ui.fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manoj.mobilepayment.MainActivity;
import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentProductitemlistBinding;
import com.manoj.mobilepayment.db.entity.ProductEntity;
import com.manoj.mobilepayment.model.CartProduct;
import com.manoj.mobilepayment.model.Product;
import com.manoj.mobilepayment.ui.adaptor.MyProductItemRecyclerViewAdapter;
import com.manoj.mobilepayment.ui.callbacks.AddToCardProductCallBack;
import com.manoj.mobilepayment.ui.callbacks.ProductCallback;
import com.manoj.mobilepayment.utility.SeparatorDecoration;
import com.manoj.mobilepayment.viewmodel.ProductListViewModel;

import java.util.HashMap;
import java.util.List;


public class ProductListFragment extends Fragment implements LifecycleOwner {

    public static final String TAG = "ProductListFragment";
    private MyProductItemRecyclerViewAdapter mProductAdaptor;
    private FragmentProductitemlistBinding mBinding;
    private HashMap<String, CartProduct> addToCardItemList = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_productitemlist, container, false);
        mProductAdaptor = new MyProductItemRecyclerViewAdapter(addToCardCallback);
        mBinding.recyclerviewProductlist.setAdapter(mProductAdaptor);
        mBinding.recyclerviewProductlist.addItemDecoration(new SeparatorDecoration(this.getContext(), Color.GRAY, 1.5f));
        mBinding.setProceedcalback(callback);
        cleanAddToCart();
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductListViewModel viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        updateUI(viewModel);

    }

    private void updateUI(ProductListViewModel viewModel) {
        viewModel.getProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> productEntities) {
                if (productEntities!=null){
                    mBinding.setIsLoading(false);
                    mProductAdaptor.setmProductList(productEntities);
                }else{
                    mBinding.setIsLoading(true);
                }
                mBinding.executePendingBindings();
            }
        });
    }

    public void cleanAddToCart(){
        if (addToCardItemList!=null){
            addToCardItemList.clear();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    ProductCallback callback = new ProductCallback() {
        @Override
        public void proceedCallBack() {
            //======== Transfer fragment =======//
            if (addToCardItemList.size()!=0)
            ((MainActivity)getActivity()).detailsFragment(addToCardItemList);
            else
                Toast.makeText(getContext(), "Please add item to card first and try again", Toast.LENGTH_SHORT).show();
        }
    };
    AddToCardProductCallBack addToCardCallback = new AddToCardProductCallBack() {
        @Override
        public void itemClicked(Product product) {
            // String id = String.valueOf(product.getId());
            CartProduct cartProduct = new CartProduct();
            cartProduct.setItemsCount(1);
            cartProduct.setProduct(product);
            addToCardItemList.put(String.valueOf(product.getId()), cartProduct);
            Toast.makeText(getContext(), "'"+ String.valueOf(product.getTitle())+"' item has added to card successfully!", Toast.LENGTH_SHORT).show();
        }
    };
}
