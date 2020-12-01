package com.add.ad.presentation.ui.fragment.main.mypage.etc.alert;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentAlertBinding;
import com.add.ad.presentation.adapter.AppliedAdAdapter;
import com.add.ad.presentation.adapter.NotificationAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.alert.AlertViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AlertFragment extends BaseFragment<FragmentAlertBinding, AlertViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_alert);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        viewModel.getNotificationList();

        return v;
    }

    @Override
    protected Class<AlertViewModel> getViewModelClass() {
        return AlertViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.alertEvent.observe(this, mVoid ->{
            binding.alertProgressBar.setVisibility(View.GONE);
            binding.alertRecyclerView.setAdapter(new NotificationAdapter(viewModel.alertList.getValue(), viewModel));
            binding.alertRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
    }
}