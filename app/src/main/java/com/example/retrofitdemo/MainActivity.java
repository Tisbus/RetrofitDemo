package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitdemo.Model.MovieApiResponse;
import com.example.retrofitdemo.Model.Result;
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
    private List<Result> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                   if(list != null){
                       adapterRetro.setList(list);
                       adapterRetro.notifyDataSetChanged();
                   }

               }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }
}