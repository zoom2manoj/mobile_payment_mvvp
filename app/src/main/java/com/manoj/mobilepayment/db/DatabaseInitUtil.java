package com.manoj.mobilepayment.db;

import com.manoj.mobilepayment.db.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

public class DatabaseInitUtil {


    static  void initializeDb(AppDatabase db){

        List<ProductEntity> prodcutList = new ArrayList<>();

        addData(prodcutList);
        insertData(db, prodcutList);

    }

    private static void insertData(AppDatabase db, List<ProductEntity> prodcutList) {
        db.beginTransaction();
        try{
            db.productDao().insertAll(prodcutList);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }

    private static void addData(List<ProductEntity> prodcutList) {
        Random rnd = new Random();
        for (int i=0; i<30; i++){
            ProductEntity entity = new ProductEntity();
            entity.setId(i+1);
            entity.setTitle("Product Name "+(i+1));
            entity.setDetails("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."+(i+1));
            entity.setPrice(rnd.nextInt(1000));
            prodcutList.add(entity);

        }
    }
}
