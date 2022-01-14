package com.example.retrofitdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.Model.Result;

import java.util.List;

public class AdapterRetro extends RecyclerView.Adapter<AdapterRetro.RetroViewHolder> {

    private List<Result> list;
    private Context context;


    public AdapterRetro(List<Result> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setList(List<Result> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RetroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new RetroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetroViewHolder holder, int position) {
        Result result = list.get(position);
        String urlImg = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
        Glide.with(context)
                .load(urlImg)
                .into(holder.imageViewPoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RetroViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPoster;

        public RetroViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Result result = list.get(position);
                        Intent goDetail = new Intent(context, DetailActivity.class);
                        goDetail.putExtra("result", result);
                        context.startActivity(goDetail);
                    }
                }
            });
        }
    }
}
