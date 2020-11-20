package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemLikeAdBinding;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.presentation.viewModel.mypage.myad.LikeAdViewModel;

import java.util.ArrayList;

public class LikeAdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final ArrayList<ResponseMyAdInfo> likeAdItems;
    LikeAdViewModel likeAdViewModel;

    public LikeAdAdapter(ArrayList<ResponseMyAdInfo> likeAdItems,LikeAdViewModel likeAdViewModel) {
        this.likeAdItems = likeAdItems;
        this.likeAdViewModel = likeAdViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLikeAdBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_like_ad, parent, false);
        return new LikeAdAdapter.LikeAdViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LikeAdAdapter.LikeAdViewHolder){
            ((LikeAdAdapter.LikeAdViewHolder)holder).bind(likeAdItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return likeAdItems.size();
    }

    public class LikeAdViewHolder extends RecyclerView.ViewHolder {
        ItemLikeAdBinding binding;
        ResponseMyAdInfo item;
        int position;

        public LikeAdViewHolder(ItemLikeAdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseMyAdInfo item, int position) {
            this.position = position;
            this.item = item;
            binding.setVh(this);
            binding.setLikeItem(item);
        }

    }
}
