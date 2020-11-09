package com.add.ad.presentation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemFeedBinding;
import com.add.ad.entity.response.ResponseFeedInfo;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseFeedInfo> feedItems;
    FeedViewModel feedViewModel;

    public FeedAdapter(ArrayList<ResponseFeedInfo> feedItems, FeedViewModel feedViewModel) {
        this.feedItems = feedItems;
        this.feedViewModel = feedViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_feed ,parent,false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FeedViewHolder) {
            ((FeedViewHolder) holder).bind(feedItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(feedItems).size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedBinding binding;
        ResponseFeedInfo item;
        int position;

        FeedViewHolder(ItemFeedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseFeedInfo item, int position) {
            this.item = item;
            this.position = position;
            binding.setVh(this);
            binding.setFeedItem(item);
            binding.executePendingBindings();
        }

        public void clickImage(View v){
            feedViewModel.feedDetailEvent.call();
            feedViewModel.getDetailFeed(position);
        }
    }
}
