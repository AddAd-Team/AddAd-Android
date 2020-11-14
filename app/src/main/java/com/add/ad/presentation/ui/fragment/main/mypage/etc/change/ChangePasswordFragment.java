package com.add.ad.presentation.ui.fragment.main.mypage.etc.change;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentChangePasswordBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.mypage.change.ChangePasswordViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class ChangePasswordFragment extends BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_change_password);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);

        return v;
    }

    @Override
    protected Class<ChangePasswordViewModel> getViewModelClass() {
        return ChangePasswordViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.pwChangeEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_changePasswordFragment_to_mainFragment);
        });
    }
}