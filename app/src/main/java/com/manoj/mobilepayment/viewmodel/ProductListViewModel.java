package com.manoj.mobilepayment.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.manoj.mobilepayment.db.DatabaseCreator;
import com.manoj.mobilepayment.db.entity.ProductEntity;

import java.util.List;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

public class ProductListViewModel extends AndroidViewModel {


    private static final MutableLiveData ABSENT = new MutableLiveData();{
        ABSENT.setValue(null);
    }

    private final LiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(Application application){
        super(application);
        final DatabaseCreator databaseCreator =  DatabaseCreator.getsIntance(this.getApplication());

        final LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();


        //============ Load live database for UI use ===//
        mObservableProducts = Transformations.switchMap(databaseCreated, new Function<Boolean, LiveData<List<ProductEntity>>>() {
            @Override
            public LiveData<List<ProductEntity>> apply(Boolean input) {
                if (!Boolean.TRUE.equals(input)){
                    return ABSENT;
                }else{
                    return  databaseCreator.getDatabase().productDao().loadAllProducts();
                }
            }
        });

        databaseCreator.createDatbase(this.getApplication());

    }

    //===== Expose Data for UI ===//
    public LiveData<List<ProductEntity>> getProducts(){
        return mObservableProducts;
    }

}
