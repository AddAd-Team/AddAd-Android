package com.add.ad.presentation.ui.fragment.main.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFeedBinding;
import com.add.ad.entity.response.ResponseFeedInfo;
// import com.add.ad.presentation.adapter.FeedAdapter;
import com.add.ad.presentation.adapter.FeedAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FeedFragment extends BaseFragment<FragmentFeedBinding, FeedViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_feed);
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
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.feedListEvent.observe(this, mVoid -> {
            binding.feedRecyclerView.setAdapter(new FeedAdapter(viewModel.feedList.getValue(),viewModel));
            binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });

        viewModel.feedDetailEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_FeedDetailFragment);
        });
    }

}