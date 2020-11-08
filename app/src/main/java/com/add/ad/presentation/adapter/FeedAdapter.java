package com.add.ad.presentation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemFeedBinding;
import com.add.ad.entity.response.ResponseFeedInfo;

import java.util.ArrayList;
import java.util.Objects;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseFeedInfo> feedItems;

    public FeedAdapter(ArrayList<ResponseFeedInfo> feedItems) {
        this.feedItems = feedItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("onCreateviewHolder", String.valueOf(viewType));
        ItemFeedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_feed ,parent,false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("onBindViewHolder", holder.toString());
        if(holder instanceof FeedViewHolder) {
            ((FeedViewHolder) holder).bind(feedItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(feedItems).size();
    }

    public void setFeedItems(MutableLiveData<ArrayList<ResponseFeedInfo>> feedItem){
        Log.d("setFeedItems","sdfs");
        this.feedItems = feedItem.getValue();
        notifyDataSetChanged();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedBinding binding;
        ResponseFeedInfo item;
        FeedViewHolder(ItemFeedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            Log.d("viewholder", String.valueOf(binding));
        }

        void bind(ResponseFeedInfo item) {
            this.item = item;
            binding.setFeedItem(item);
        }

    }
}
