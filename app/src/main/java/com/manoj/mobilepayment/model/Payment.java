package com.manoj.mobilepayment.model;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.manoj.mobilepayment.BR;

/**
 * Created by MBaghela1 on 11/10/2017.
 */

public class Payment extends BaseObservable {
    private String amount ="";

    public Payment(){

    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
    }
}
