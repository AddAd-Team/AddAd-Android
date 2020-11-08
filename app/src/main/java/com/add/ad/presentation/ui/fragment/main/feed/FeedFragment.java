package com.add.ad.presentation.ui.fragment.main.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFeedBinding;
import com.add.ad.entity.response.ResponseFeedInfo;
// import com.add.ad.presentation.adapter.FeedAdapter;
import com.add.ad.presentation.adapter.FeedAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FeedFragment extends BaseFragment<FragmentFeedBinding> {
    private FeedViewModel feedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_feed);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        feedViewModel = new ViewModelProvider(requireActivity()).get(FeedViewModel.class);

        binding.setVm(feedViewModel);
        feedViewModel.getFeed();


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feedViewModel.feedListEvent.observe(this, mVoid -> {
            binding.feedRecyclerView.setAdapter(new FeedAdapter(feedViewModel.feedList.getValue()));
            binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });

    }
}