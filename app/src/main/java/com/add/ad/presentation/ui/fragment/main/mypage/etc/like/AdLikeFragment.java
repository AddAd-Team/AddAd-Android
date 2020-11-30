package com.add.ad.presentation.ui.fragment.main.mypage.etc.like;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentAdLikeBinding;
import com.add.ad.presentation.adapter.LikeAdAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.myad.LikeAdViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdLikeFragment extends BaseFragment<FragmentAdLikeBinding, LikeAdViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_ad_like);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        viewModel.getLikeAdList();

        viewModel.likeAdResult = new MutableLiveData<>(true);

        return v;
    }



    @Override
    protected Class<LikeAdViewModel> getViewModelClass() {
        return LikeAdViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.likeAdEvent.observe(this, mVoid -> {
            binding.adLikeRecyclerView.setAdapter(new LikeAdAdapter(viewModel.likeAdList.getValue(), viewModel));
            binding.adLikeRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
        viewModel.stopProgressEvent.observe(this, mVoid -> {
            binding.likeAdProgressBar.setVisibility(View.GONE);
        });
    }
}