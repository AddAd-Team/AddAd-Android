package com.add.ad.presentation.ui.fragment.main.mypage.etc.myad.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentAdLikeBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.myad.MyAdViewModel;

public class ApplyAdFragment extends BaseFragment<FragmentAdLikeBinding, MyAdViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_ad_apply);
        View v = super.onCreateView(inflater, container, savedInstanceState);

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

    }
}