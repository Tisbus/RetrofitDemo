package com.example.retrofitdemo.service;

import com.example.retrofitdemo.Model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/3/movie/popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String apiKey);
}
