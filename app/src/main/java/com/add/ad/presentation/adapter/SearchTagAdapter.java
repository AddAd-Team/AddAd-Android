package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemSearchTagBinding;
import com.add.ad.presentation.viewModel.search.SearchViewModel;

import java.util.ArrayList;

public class SearchTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> tagList;
    SearchViewModel searchViewModel;

    public SearchTagAdapter(ArrayList<String> tagList, SearchViewModel searchViewModel) {
        this.tagList = tagList;
        this.searchViewModel = searchViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchTagViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchTagViewHolder)holder).bind(tagList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public class SearchTagViewHolder extends RecyclerView.ViewHolder {
        ItemSearchTagBinding binding;
        int position;

        public SearchTagViewHolder(ItemSearchTagBinding itemSearchTagBinding) {
            super(itemSearchTagBinding.getRoot());
            this.binding = itemSearchTagBinding;
        }

        void bind(String item, int position) {
            binding.setVh(this);
            binding.searchTagItemTv.setText(item);
            this.position = position;
        }

        public void clickTag(View v){
            searchViewModel.searchTag(position);
        }
    }
}
