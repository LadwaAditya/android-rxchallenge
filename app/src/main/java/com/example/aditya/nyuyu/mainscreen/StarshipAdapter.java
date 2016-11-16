package com.example.aditya.nyuyu.mainscreen;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aditya.nyuyu.R;
import com.example.aditya.nyuyu.data.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StarshipAdapter extends RecyclerView.Adapter<StarshipAdapter.ViewHolder> {

    private List<Result> results;

    public StarshipAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_starship, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);

        holder.starship.setText(result.getName());
        holder.cost.setText(result.getCostInCredits());
        holder.films.setText(result.getFilms().get(0));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_starship)
        AppCompatTextView starship;
        @BindView(R.id.txt_cost)
        AppCompatTextView cost;
        @BindView(R.id.txt_films)
        AppCompatTextView films;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
