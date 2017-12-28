package com.manoj.mobilepayment.paymentstrategy;

import android.util.Log;

import com.manoj.mobilepayment.model.NetBanking;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public class InternetPayment implements PaymentOption {

    private NetBanking netBanking;
    public InternetPayment(NetBanking netBanking){
        this.netBanking = netBanking;
    }

    @Override
    public void pay(int amount) {
        Log.d("payment opotion", "internet payment option");
    }
}
