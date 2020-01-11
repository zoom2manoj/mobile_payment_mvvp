package com.manoj.mobilepayment.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentPaymentResultBinding;
import com.manoj.mobilepayment.model.Payment;


public class PaymentResultFragment extends Fragment {

    private String amount = "";
    private Payment payment;
    public PaymentResultFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amount = (String)getArguments().get("amount");
        payment = new Payment();
        payment.setAmount(amount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPaymentResultBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_result, container, false);
        binding.setPayment(payment);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
