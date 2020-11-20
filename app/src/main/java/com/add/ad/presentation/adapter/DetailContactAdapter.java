package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemDetailContactAdBinding;
import com.add.ad.databinding.ItemMyAdBinding;
import com.add.ad.entity.response.ResponseDetailContactAdInfo;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.presentation.viewModel.search.SearchViewModel;

import java.util.ArrayList;

public class DetailContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseDetailContactAdInfo> contactAdItems;
    SearchViewModel searchViewModel;

    public DetailContactAdapter(ArrayList<ResponseDetailContactAdInfo> contactAdItems, SearchViewModel searchViewModel) {
        this.contactAdItems = contactAdItems;
        this.searchViewModel = searchViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailContactAdBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_detail_contact_ad, parent, false);
        return new DetailContactAdapter.DetailContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DetailContactAdapter.DetailContactViewHolder) {
            ((DetailContactAdapter.DetailContactViewHolder) holder).bind(contactAdItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return contactAdItems.size();
    }

    public class DetailContactViewHolder extends RecyclerView.ViewHolder {
        ItemDetailContactAdBinding binding;
        ResponseDetailContactAdInfo item;
        int position;

        DetailContactViewHolder(ItemDetailContactAdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseDetailContactAdInfo item, int position) {
            this.item = item;
            this.position = position;
            binding.setContactAdItem(item);
        }
    }
}
