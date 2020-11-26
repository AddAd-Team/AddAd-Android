package com.add.ad.presentation.ui.fragment.main.mypage.etc.myad.manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentAdApplyBinding;
import com.add.ad.presentation.adapter.AccessAdAdapter;
import com.add.ad.presentation.adapter.AppliedAdAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.myad.ApplyAdViewModel;

public class ApplyAdFragment extends BaseFragment<FragmentAdApplyBinding, ApplyAdViewModel> {
    AccessAdAdapter accessAdAdapter;
    AppliedAdAdapter appliedAdAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_ad_apply);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        accessAdAdapter = new AccessAdAdapter(viewModel);
        appliedAdAdapter = new AppliedAdAdapter(viewModel.appliedAdList.getValue(), viewModel, accessAdAdapter);

        if (getArguments() != null) {
            viewModel.position.setValue(getArguments().getString("position"));
        }

        binding.adAccessRecyclerView.setAdapter(accessAdAdapter);
        binding.adAccessRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        viewModel.getAppliedList();

        return v;
    }

    @Override
    protected Class<ApplyAdViewModel> getViewModelClass() {
        return ApplyAdViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.appliedListEvent.observe(this, mVoid -> {
            binding.adApplyRecyclerView.setAdapter(new AppliedAdAdapter(viewModel.appliedAdList.getValue(), viewModel, accessAdAdapter));
            binding.adApplyRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
    }
}