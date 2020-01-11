package com.manoj.mobilepayment.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.manoj.mobilepayment.db.dao.ProductDao;
import com.manoj.mobilepayment.db.entity.ProductEntity;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

@Database(entities = {ProductEntity.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    static final String DATABASE_NAME = "payment_module-db";

    public abstract ProductDao productDao();

}
