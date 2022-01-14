package com.fatihkoc.featuredKenny;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkoc.featuredKenny.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class gamerAdapter extends RecyclerView.Adapter<gamerAdapter.gamerHolder> {
    ArrayList<gamer> gamerArrayList;

    public gamerAdapter(ArrayList<gamer> gamerArrayList) {
        this.gamerArrayList = gamerArrayList;
    }

    @NonNull
    @Override
    public gamerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new gamerHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull gamerHolder holder, int position) {
        holder.binding.idText.setText(""+gamerArrayList.get(position).id);
        holder.binding.nameText1.setText(""+gamerArrayList.get(position).isim);
        holder.binding.skorText1.setText(""+gamerArrayList.get(position).skor);
        holder.binding.timeText1.setText(""+gamerArrayList.get(position).time);
        holder.binding.difficultyText.setText(""+gamerArrayList.get(position).difficulty);

    }

    @Override
    public int getItemCount() {
        return gamerArrayList.size();
    }

    public class gamerHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;
        public gamerHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
