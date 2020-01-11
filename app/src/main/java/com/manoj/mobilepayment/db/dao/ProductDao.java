package com.manoj.mobilepayment.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.manoj.mobilepayment.db.entity.ProductEntity;

import java.util.List;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> products);

    @Query("SELECT * FROM products where id = :productId")
    LiveData<ProductEntity> loadProduct(int productId);


    @Query("select * from products where id = :productId")
    ProductEntity loadProductSync(int productId);
}
