package com.example.retrofitdemo.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class RetrofitDataBase extends RoomDatabase {
    private final static String DB_NAME = "retrofit";
    private final static Object LOCK = new Object();
    private static RetrofitDataBase dataBase;
    public static RetrofitDataBase getInstance(Context context){
        synchronized (LOCK){
            if(dataBase == null){
                dataBase = Room.databaseBuilder(context.getApplicationContext(), RetrofitDataBase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();

            }
        }
        return dataBase;
    }
    public abstract RetrofitDao retrofitDao();
}
