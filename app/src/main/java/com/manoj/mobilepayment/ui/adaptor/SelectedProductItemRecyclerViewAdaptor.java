package com.manoj.mobilepayment.ui.adaptor;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentSelectedproductitemBinding;
import com.manoj.mobilepayment.model.CartProduct;
import com.manoj.mobilepayment.model.Product;
import com.manoj.mobilepayment.model.ProductQuantity;
import com.manoj.mobilepayment.ui.callbacks.ISpinnerDataChange;
import com.manoj.mobilepayment.ui.fragments.SelectedProductDetailsFragment;

import java.util.List;

/**
 * Created by MBaghela1 on 11/10/2017.
 */

public class SelectedProductItemRecyclerViewAdaptor extends RecyclerView.Adapter<SelectedProductItemRecyclerViewAdaptor.ViewHolder> implements ISpinnerDataChange {

    private SelectedProductDetailsFragment selectedProductDetailsFragment;
    private List<CartProduct> addToCardItemList;


    public SelectedProductItemRecyclerViewAdaptor(SelectedProductDetailsFragment selectedProductDetailsFragment,
                                                  List<CartProduct> addToCardItemList){
        this.selectedProductDetailsFragment  = selectedProductDetailsFragment;
        this.addToCardItemList = addToCardItemList;
        totalProductAmount();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FragmentSelectedproductitemBinding binding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.fragment_selectedproductitem,
                parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartProduct cartProduct= addToCardItemList.get(position);
        Product product = cartProduct.getProduct();
        holder.binding.setProduct(product);
        ProductQuantity productQuantity = new ProductQuantity(this, position);
        holder.binding.setQuantity(productQuantity);
        Log.d("be selection ", String.valueOf(productQuantity.getSelectedData()));
        holder.binding.executePendingBindings();
        Log.d("selection ", String.valueOf(productQuantity.getSelectedData()));

    }

    @Override
    public int getItemCount() {
        return addToCardItemList==null?0:addToCardItemList.size();
    }

    public void totalProductAmount() {


        int totalAmout = 0;
        for (CartProduct cartProduct: addToCardItemList){
            Product product = cartProduct.getProduct();
            int items = cartProduct.getItemsCount();
            int price = product.getPrice();
            totalAmout+=(items*price);
        }
        selectedProductDetailsFragment.updateTotalAmount(totalAmout);

    }

    @Override
    public void updateProductQuantity(int itemPosition, int position) {
        Log.d("data", itemPosition+" and "+position);
        CartProduct cartProduct = addToCardItemList.get(itemPosition);
        cartProduct.setItemsCount(position);
        addToCardItemList.set(itemPosition, cartProduct);
        totalProductAmount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentSelectedproductitemBinding binding;
        public ViewHolder(FragmentSelectedproductitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
