package com.manoj.mobilepayment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;

import com.manoj.mobilepayment.model.CartProduct;
import com.manoj.mobilepayment.ui.fragments.PaymentOptionFragment;
import com.manoj.mobilepayment.ui.fragments.PaymentResultFragment;
import com.manoj.mobilepayment.ui.fragments.ProductListFragment;
import com.manoj.mobilepayment.ui.fragments.SelectedProductDetailsFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ProductListFragment fragment = new ProductListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit();
        }
    }


    public void detailsFragment(HashMap<String, CartProduct> addToCardItemList){
        SelectedProductDetailsFragment detailsFragment = new SelectedProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("products", addToCardItemList);
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        detailsFragment, SelectedProductDetailsFragment.TAG).commit();
    }

    public void paymentFragment(String value) {
        PaymentOptionFragment paymentFragment = new PaymentOptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("amount", value);
        paymentFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("payment")
                .replace(R.id.fragment_container,
                        paymentFragment, PaymentOptionFragment.TAG).commit();
    }

    public void paymentProceedFragment(String amount) {
        shortMessageForUser(amount);
    }


    private void shortMessageForUser(final String amount) {


        new AsyncTask<String, Void , Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressBar = new ProgressDialog(MainActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("Please integrate payment SDK gateway for banking transcation!");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.show();

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressBar.dismiss();

                PaymentResultFragment paymentResultFragment = new PaymentResultFragment();
                Bundle bundle = new Bundle();
                bundle.putString("amount", amount);
                paymentResultFragment.setArguments(bundle);
                FragmentManager mFragmentManager = getSupportFragmentManager();
                mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                mFragmentManager
                        .beginTransaction()
                        .addToBackStack("paymentproceed")
                        .replace(R.id.fragment_container,
                                paymentResultFragment, PaymentOptionFragment.TAG).commit();
            }

            @Override
            protected Void doInBackground(String... strings) {

                try{
                    Thread.sleep(6000);
                }catch (Exception e){

                }
                return null;
            }
        }.execute("");

    }
}
