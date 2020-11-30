package com.add.ad.presentation.ui.fragment.main.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStoreOwner;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFeedDetailBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

public class FeedDetailFragment extends BaseFragment<FragmentFeedDetailBinding, FeedViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_feed_detail);

        return super.onCreateView(inflater, container, savedInstanceState);
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
        viewModel.detailFeed = new MutableLiveData<>();

        viewModel.stopProgressEvent.observe(this, mVoid -> {
            binding.detailFeedProgressBar.setVisibility(View.GONE);
            binding.feedDetailTagTv.setVisibility(View.VISIBLE);
            binding.feedDetailRecruitEndDateTv.setVisibility(View.VISIBLE);
            binding.feedDetailAdEndDateTv.setVisibility(View.VISIBLE);
            binding.feedDetailPriceTv.setVisibility(View.VISIBLE);
            binding.feedDetailDescriptionTv.setVisibility(View.VISIBLE);
            binding.feedDetailApplyBtn.setVisibility(View.VISIBLE);
        });
    }
}