package com.manoj.mobilepayment.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.manoj.mobilepayment.db.AppDatabase.DATABASE_NAME;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

public class DatabaseCreator {

    private static DatabaseCreator sIntance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private AppDatabase mDB;

    private final AtomicBoolean mInitinizing = new AtomicBoolean(true);

    private static final Object LOCK = new Object();

    public synchronized  static  DatabaseCreator getsIntance(Context context){

        if (sIntance==null){
            synchronized (LOCK){
                if (sIntance==null){
                    sIntance = new DatabaseCreator();
                }
            }
        }

        return  sIntance;
    }

    public MutableLiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    public AppDatabase getDatabase(){
        return  mDB;
    }

    @SuppressLint("StaticFieldLeak")
    public void createDatbase(Context context){

        if (!mInitinizing.weakCompareAndSet(true, false)){
            return;
        }

        mIsDatabaseCreated.setValue(false);

        new AsyncTask<Context, Void, Void>(){

            @Override
            protected Void doInBackground(Context... contexts) {
                Context context = contexts[0].getApplicationContext();
                context.deleteDatabase(DATABASE_NAME);
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                addDelay();
                DatabaseInitUtil.initializeDb(db);
                mDB = db;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mIsDatabaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }


    private void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {}
    }


}
