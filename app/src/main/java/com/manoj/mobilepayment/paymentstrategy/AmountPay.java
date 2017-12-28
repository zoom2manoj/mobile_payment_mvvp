package com.manoj.mobilepayment.paymentstrategy;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public class AmountPay {
    private String amount;
    public AmountPay(String amount){
        this.amount = amount;
    }

    public void pay(PaymentOption paymentOption){
        paymentOption.pay(Integer.parseInt(amount));
    }
}
