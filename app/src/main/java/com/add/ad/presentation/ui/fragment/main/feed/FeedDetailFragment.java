package com.add.ad.presentation.ui.fragment.main.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFeedDetailBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

public class FeedDetailFragment extends BaseFragment<FragmentFeedDetailBinding, FeedViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_feed_detail);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getFeed();

        return v;
    }

    @Override
    protected Class<FeedViewModel> getViewModelClass() {
        return FeedViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {

    }
}