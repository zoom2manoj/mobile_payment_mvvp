package com.manoj.mobilepayment.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manoj.mobilepayment.MainActivity;
import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentSelectedProductDetailsBinding;
import com.manoj.mobilepayment.model.CartProduct;
import com.manoj.mobilepayment.model.Payment;
import com.manoj.mobilepayment.ui.adaptor.SelectedProductItemRecyclerViewAdaptor;
import com.manoj.mobilepayment.ui.callbacks.PaymentOptionCallback;
import com.manoj.mobilepayment.utility.SeparatorDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SelectedProductDetailsFragment extends Fragment {
    public static final String TAG = "SelectedProductDetailsFragment";
    private List<CartProduct> itemList;
    private FragmentSelectedProductDetailsBinding binding;
    private SelectedProductItemRecyclerViewAdaptor selectedProductItemRecyclerViewAdaptor;
    private Payment payment = new Payment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HashMap<String, CartProduct> addToCardItemList = (HashMap<String, CartProduct>) getArguments().getSerializable("products");
        itemList = new ArrayList<>(addToCardItemList.values());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_selected_product_details,
                container, false);

        binding.setPaymentamount(payment);
        selectedProductItemRecyclerViewAdaptor  =new SelectedProductItemRecyclerViewAdaptor(this, itemList);
        binding.rvSelectedproductlist.setAdapter(selectedProductItemRecyclerViewAdaptor);
        binding.rvSelectedproductlist.addItemDecoration(new SeparatorDecoration(this.getContext(), Color.GRAY, 1.5f));
        binding.setPaymentcallback(paymentOptionCallback);
        binding.executePendingBindings();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void updateTotalAmount(int value){

        payment.setAmount(String.valueOf(value));

    }


    PaymentOptionCallback paymentOptionCallback = new PaymentOptionCallback() {
        @Override
        public void paymentOption(String value) {
            Log.d("value", value);
            if (Integer.parseInt(value)>00)
                ((MainActivity)getActivity()).paymentFragment(value);
            else
                Toast.makeText(getContext(), "Please add item to card first and try again", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
