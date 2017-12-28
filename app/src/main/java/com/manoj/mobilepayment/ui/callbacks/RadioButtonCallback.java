package com.manoj.mobilepayment.ui.callbacks;

import android.view.View;

/**
 * Created by MBaghela1 on 11/13/2017.
 */

public interface RadioButtonCallback {

    boolean checkedFlage(View view);
    void paymentProceed(String amount, boolean ccFlage, boolean dcFlage, boolean netbankingFlage);
}
