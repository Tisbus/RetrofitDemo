package com.example.retrofitdemo.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RetrofitDao {
    @Query("SELECT * FROM retrofit WHERE id==:id")
    LiveData<List<Result>> getAllResultForId(int id);
    @Query("SELECT * FROM retrofit")
    LiveData<List<Result>> getAllResults();
    @Query("DROP TABLE retrofit")
    public void deleteAll();
    @Insert
    public void insertAll(Result result);
}
