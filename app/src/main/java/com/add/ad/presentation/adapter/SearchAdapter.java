package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemSearchCreatorBinding;
import com.add.ad.entity.response.ResponseSearchInfo;
import com.add.ad.presentation.viewModel.search.SearchViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ResponseSearchInfo> searchItems;
    SearchViewModel searchViewModel;

    public SearchAdapter(ArrayList<ResponseSearchInfo> searchItems, SearchViewModel searchViewModel) {
        this.searchItems = searchItems;
        this.searchViewModel = searchViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchCreatorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search_creator ,parent,false);
        return new SearchAdapter.SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SearchAdapter.SearchViewHolder) {
            ((SearchAdapter.SearchViewHolder) holder).bind(searchItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(searchItems).size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ItemSearchCreatorBinding binding;
        ResponseSearchInfo item;
        int position;

        public SearchViewHolder(ItemSearchCreatorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseSearchInfo item, int position) {
            this.position = position;
            this.item = item;
            binding.setVh(this);
            binding.setSearchItem(item);
        }

        public void clickCreator(View v){
            searchViewModel.searchDetailCreator(position);
            searchViewModel.searchDetailEvent.call();
        }
    }
}
