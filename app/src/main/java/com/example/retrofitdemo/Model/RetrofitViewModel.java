package com.example.retrofitdemo.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RetrofitViewModel extends AndroidViewModel {

    private LiveData<List<Result>> listResult;
    private RetrofitDataBase dataBase;

    public LiveData<List<Result>> getListResult() {
        return listResult;
    }

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
        dataBase = RetrofitDataBase.getInstance(getApplication());
        listResult = dataBase.retrofitDao().getAllResults();
    }

    public void insert(Result result){new InsertTask().execute(result);}
    public void deleteAll(){new DeleteAllTask().execute();}

    public class InsertTask extends AsyncTask<Result, Void, Void>{
        @Override
        protected Void doInBackground(Result... results) {
            if(results != null && results.length > 0){
                dataBase.retrofitDao().insertAll(results[0]);
            }
            return null;
        }
    }
    public class DeleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            if(voids != null && voids.length > 0){
                dataBase.retrofitDao().deleteAll();
            }
            return null;
        }
    }

}
