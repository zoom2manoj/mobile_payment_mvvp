package com.manoj.mobilepayment.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.manoj.mobilepayment.BR;
import com.manoj.mobilepayment.ui.callbacks.ISpinnerDataChange;

/**
 * Created by MBaghela1 on 11/16/2017.
 */


public class ProductQuantity extends BaseObservable {

    private String selectedData;
    private int itemPosition;
    private ISpinnerDataChange iSpinnerDataChange;
    public ProductQuantity(ISpinnerDataChange iSpinnerDataChange, int itemPosition){
        this.itemPosition = itemPosition;
        this.iSpinnerDataChange = iSpinnerDataChange;
    }



    public void setSelectedData(String selectedData) {
        this.selectedData = selectedData;
        iSpinnerDataChange.updateProductQuantity(itemPosition, Integer.parseInt(selectedData));
        notifyPropertyChanged(BR.selectedData);
    }

    @Bindable
    public String getSelectedData() {
        return selectedData;
    }




    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, String newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }
    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }

}
