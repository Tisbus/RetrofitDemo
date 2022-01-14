package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitdemo.Model.MovieApiResponse;
import com.example.retrofitdemo.Model.Result;
import com.example.retrofitdemo.Model.RetrofitViewModel;
import com.example.retrofitdemo.service.ApiService;
import com.example.retrofitdemo.service.GsonConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterRetro adapterRetro;
    private RecyclerView recyclerView;
    private RetrofitViewModel viewModel;
    private List<Result> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RetrofitViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewMain);

        getPopularMovies();
        adapterRetro = new AdapterRetro(list, this);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setAdapter(adapterRetro);
    }

    public void getPopularMovies(){
        ApiService movie = GsonConverter.getInstance();

        Call<MovieApiResponse> call = movie.getPopularMovies(getString(R.string.api_key));

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
               if(movieApiResponse != null && movieApiResponse.getResults() != null){
                   list = movieApiResponse.getResults();
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }

    public void getData(){
        if(list != null){
            LiveData<List<Result>> getListLive = viewModel.getListResult();
            getListLive.observe(this, new Observer<List<Result>>() {
                @Override
                public void onChanged(List<Result> results) {
                    adapterRetro.setList(results);
                    adapterRetro.notifyDataSetChanged();
                }
            });

        }
    }
}