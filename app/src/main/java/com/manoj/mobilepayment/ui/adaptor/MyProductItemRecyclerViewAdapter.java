package com.manoj.mobilepayment.ui.adaptor;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentProductitemBinding;
import com.manoj.mobilepayment.model.Product;
import com.manoj.mobilepayment.ui.callbacks.AddToCardProductCallBack;

import java.util.List;
import java.util.Objects;


public class MyProductItemRecyclerViewAdapter extends RecyclerView.Adapter<MyProductItemRecyclerViewAdapter.ViewHolder>  {

    List<? extends Product> mProductList;
    AddToCardProductCallBack addToCardCallback;
    public MyProductItemRecyclerViewAdapter( AddToCardProductCallBack addToCardCallback) {
        this.addToCardCallback = addToCardCallback;
    }

    public void setmProductList(final List<? extends Product> productList) {
        if (mProductList == null) {
            mProductList = productList;
            notifyItemRangeInserted(0, productList.size());
        } else {

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getId() == productList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getId() == productList.get(newItemPosition).getId() &&
                            Objects.equals(mProductList.get(oldItemPosition).getTitle(), productList.get(newItemPosition).getTitle()) &&
                            Objects.equals(mProductList.get(oldItemPosition).getDetails(), productList.get(newItemPosition).getDetails()) &&
                            mProductList.get(oldItemPosition).getPrice() == productList.get(newItemPosition).getPrice();
                }
            });

            mProductList = productList;
            result.dispatchUpdatesTo(this);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentProductitemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.fragment_productitem,
                parent, false);

        binding.setCallbacks(addToCardCallback);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.binding.setProduct(mProductList.get(position));

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        FragmentProductitemBinding binding;

        public ViewHolder(FragmentProductitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
