package com.manoj.mobilepayment.ui.callbacks;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.manoj.mobilepayment.BR;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public class RadioButtonInflateCallback extends BaseObservable {

    private boolean ccFlage;
    private boolean dcFlage;
    private boolean netbankingFlage;
    private String[] array;
    private String amount;

    @Bindable
    public boolean isCcFlage() {
        return ccFlage;
    }

    public void setCcFlage(boolean ccFlage) {
        this.ccFlage = ccFlage;
        this.dcFlage = !ccFlage;
        this.netbankingFlage = !ccFlage;
        notifyPropertyChanged(BR.ccFlage);
        notifyPropertyChanged(BR.dcFlage);
        notifyPropertyChanged(BR.netbankingFlage);

    }
    @Bindable
    public boolean isDcFlage() {
        return dcFlage;
    }

    public void setDcFlage(boolean dcFlage) {
        this.dcFlage = dcFlage;
        this.ccFlage = !dcFlage;
        this.netbankingFlage = !dcFlage;
        notifyPropertyChanged(BR.ccFlage);
        notifyPropertyChanged(BR.dcFlage);
        notifyPropertyChanged(BR.netbankingFlage);
    }

    @Bindable
    public boolean isNetbankingFlage() {
        return netbankingFlage;
    }

    public void setNetbankingFlage(boolean netbankingFlage) {
        this.netbankingFlage = netbankingFlage;
        this.ccFlage = !netbankingFlage;
        this.dcFlage= !netbankingFlage;
        notifyPropertyChanged(BR.ccFlage);
        notifyPropertyChanged(BR.dcFlage);
        notifyPropertyChanged(BR.netbankingFlage);
    }


    @Bindable
    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
        notifyPropertyChanged(BR.array);
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
