package com.example.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.Model.Result;

public class DetailActivity extends AppCompatActivity {

    private Result result;
    private TextView textViewTitle;
    private TextView textViewDesc;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDesc = findViewById(R.id.textViewDescription);
        imageView = findViewById(R.id.imageViewPoster);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
            Log.i("result", result.getOriginalTitle());
            textViewTitle.setText(result.getOriginalTitle());
            textViewDesc.setText(result.getOverview());
            String urlImg = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
            Glide.with(this).load(urlImg)
                    .into(imageView);
        }

    }
}