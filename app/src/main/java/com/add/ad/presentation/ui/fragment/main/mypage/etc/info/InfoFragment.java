package com.add.ad.presentation.ui.fragment.main.mypage.etc.info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentInfoBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.info.InfoViewModel;

public class InfoFragment extends BaseFragment<FragmentInfoBinding, InfoViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_info);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        return v;
    }

    @Override
    protected Class<InfoViewModel> getViewModelClass() {
        return InfoViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {

    }
}