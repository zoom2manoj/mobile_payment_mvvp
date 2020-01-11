package com.manoj.mobilepayment.model;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

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




    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
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
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }

}
