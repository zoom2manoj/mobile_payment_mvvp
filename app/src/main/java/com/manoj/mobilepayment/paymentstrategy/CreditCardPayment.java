package com.manoj.mobilepayment.paymentstrategy;

import android.util.Log;

import com.manoj.mobilepayment.model.CreditCard;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public class CreditCardPayment implements PaymentOption  {


    private CreditCard creditCard;
    public CreditCardPayment(CreditCard creditCard){
        this.creditCard = creditCard;
    }

    @Override
    public void pay(int amount) {
        Log.d("payment option", "cc payment mode");
    }
}
