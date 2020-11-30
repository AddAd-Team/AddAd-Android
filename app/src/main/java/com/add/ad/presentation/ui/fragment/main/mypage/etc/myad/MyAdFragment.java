package com.add.ad.presentation.ui.fragment.main.mypage.etc.myad;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentMyAdBinding;
import com.add.ad.presentation.adapter.MyAdAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.ui.dialog.SelectDialogFragment;
import com.add.ad.presentation.viewModel.mypage.myad.MyAdViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyAdFragment extends BaseFragment<FragmentMyAdBinding, MyAdViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_my_ad);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getMyAdList();

        viewModel.myAdResult = new MutableLiveData<>(true);

        return v;
    }

    @Override
    protected Class<MyAdViewModel> getViewModelClass() {
        return MyAdViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.creatorAdEvent.observe(this, mVoid -> {
            binding.myAdApplyRecyclerView.setAdapter(new MyAdAdapter(viewModel.creatorAdList.getValue(), viewModel));
            binding.myAdApplyRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
        viewModel.advertiserAdEvent.observe(this, mVoid -> {
            binding.myAdUploadRecyclerView.setAdapter(new MyAdAdapter(viewModel.advertiserAdList.getValue(), viewModel));
            binding.myAdUploadRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
        viewModel.clickSelectEvent.observe(this, mVoid -> {
            SelectDialogFragment selectDialogFragment = new SelectDialogFragment();
            selectDialogFragment.show(requireActivity().getSupportFragmentManager(), "SelectDialogFragment");
        });
        viewModel.stopProgressEvent.observe(this, mVoid -> {
            binding.myAdProgressBar.setVisibility(View.GONE);
        });
    }
}