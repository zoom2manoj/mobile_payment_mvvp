package com.manoj.mobilepayment.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.manoj.mobilepayment.MainActivity;
import com.manoj.mobilepayment.R;
import com.manoj.mobilepayment.databinding.FragmentPaymentOptionBinding;
import com.manoj.mobilepayment.model.CreditCard;
import com.manoj.mobilepayment.model.DebitCard;
import com.manoj.mobilepayment.model.NetBanking;
import com.manoj.mobilepayment.paymentstrategy.AmountPay;
import com.manoj.mobilepayment.paymentstrategy.CreditCardPayment;
import com.manoj.mobilepayment.paymentstrategy.DebitCardPayment;
import com.manoj.mobilepayment.paymentstrategy.InternetPayment;
import com.manoj.mobilepayment.ui.callbacks.RadioButtonCallback;
import com.manoj.mobilepayment.ui.callbacks.RadioButtonInflateCallback;

public class PaymentOptionFragment extends Fragment {

    public static final String TAG = "PaymentOptionFragment";
    private String amount;
    String[] banks;

    public PaymentOptionFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amount = (String)getArguments().get("amount");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentPaymentOptionBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_payment_option, container, false);
        binding.setRadioinflateflage(inflateCallback);
        binding.setRadiocallback(callback);
        banks = getActivity().getResources().getStringArray(R.array.bank_array);
        inflateCallback.setArray(banks);
        inflateCallback.setAmount(amount);

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


    RadioButtonCallback callback = new RadioButtonCallback() {
        @Override
        public boolean checkedFlage(View view) {

            switch (view.getId()){
                case  R.id.rbcreditcard:
                    inflateCallback.setCcFlage(true);
                        break;
                case R.id.rbdebitcard:
                    inflateCallback.setDcFlage(true);
                    break;
                case R.id.rbnetbanking:
                    inflateCallback.setNetbankingFlage(true);
                    break;
            }


            return false;
        }

        @Override
        public void paymentProceed(String amount, boolean ccFlage, boolean dcFlage, boolean netbankingFlage) {


            AmountPay amountPay = new AmountPay(amount);
            //=== display loading dialog with message for integration of
            // payment gateway according to requirement*/


            if (ccFlage){

                CreditCard creditCard   = new CreditCard("Manoj Kr Baghela", "0000000000000000", "00/00", "123");
                amountPay.pay(new CreditCardPayment(creditCard));
                ((MainActivity)getActivity()).paymentProceedFragment(amount);
            }else if (dcFlage){
                DebitCard debitCard = new DebitCard("Manoj Kr Baghela", "Citibank","0000000000000000", "00/00", "123");
                amountPay.pay(new DebitCardPayment(debitCard));
                ((MainActivity)getActivity()).paymentProceedFragment(amount);
            }else if(netbankingFlage){
                NetBanking internetPayment = new NetBanking("Citibank");
                amountPay.pay(new InternetPayment(internetPayment));
                ((MainActivity)getActivity()).paymentProceedFragment(amount);
            }else{
                Toast.makeText(getContext(), "Please select any payment option then try again!", Toast.LENGTH_SHORT).show();
            }




        }


    };


    RadioButtonInflateCallback inflateCallback = new RadioButtonInflateCallback();
}
