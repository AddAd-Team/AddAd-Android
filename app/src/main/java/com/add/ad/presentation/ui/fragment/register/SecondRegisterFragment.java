package com.add.ad.presentation.ui.fragment.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSecondRegisterBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.register.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecondRegisterFragment extends BaseFragment<FragmentSecondRegisterBinding, RegisterViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_second_register);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);

        return v;
    }

    @Override
    protected Class<RegisterViewModel> getViewModelClass() {
        return RegisterViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.startLogin.observe(this, mVoid -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_SecondRegisterFragment_to_LoginFragment);
        });
    }
}