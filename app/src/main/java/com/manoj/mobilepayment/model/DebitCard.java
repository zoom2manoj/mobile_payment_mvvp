package com.manoj.mobilepayment.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.manoj.mobilepayment.BR;

/**
 * Created by MBaghela1 on 11/14/2017.
 */

public class DebitCard extends BaseObservable {

    private String bankName;
    private String name;
    private String ccNumber;
    private String expDate;
    private String cvvNumber;

    public DebitCard(String name, String bankName, String ccNumber, String expDate, String cvvNumber){
        this.setName(name);
        this.setBankName(bankName);
        this.setCcNumber(ccNumber);
        this.setExpDate(expDate);
        this.setCvvNumber(cvvNumber);
    }

    @Bindable
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
        notifyPropertyChanged(BR.bankName);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
        notifyPropertyChanged(BR.ccNumber);
    }

    @Bindable
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
        notifyPropertyChanged(BR.expDate);
    }

    @Bindable
    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
        notifyPropertyChanged(BR.cvvNumber);
    }
}
