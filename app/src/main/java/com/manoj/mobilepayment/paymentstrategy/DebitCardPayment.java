package com.manoj.mobilepayment.paymentstrategy;

import android.util.Log;

import com.manoj.mobilepayment.model.DebitCard;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public class DebitCardPayment implements PaymentOption {


    private DebitCard debitCard;
    public DebitCardPayment(DebitCard debitCard){
    this.debitCard = debitCard;
    }

    @Override
    public void pay(int amount) {
        Log.d("Payment option", "debit card option");
    }
}
