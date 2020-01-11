package com.manoj.mobilepayment.model;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.manoj.mobilepayment.BR;

/**
 * Created by MBaghela1 on 11/14/2017.
 */

public class NetBanking extends BaseObservable {
        private String bankName;

        public NetBanking(String bankName){
            this.setBankName(bankName);
        }


    @Bindable
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
        notifyPropertyChanged(BR.bankName);
    }
}
