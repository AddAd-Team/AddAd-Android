package com.add.ad.presentation.ui.fragment.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFirstRegisterBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.register.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class FirstRegisterFragment extends BaseFragment<FragmentFirstRegisterBinding, RegisterViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_first_register);
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
        viewModel.clearErrorEvent.observe(this, mVoid -> {
            binding.registerEmailEtLayout.setError("");
            binding.registerPwCheckEtLayout.setError("");
            binding.registerPwEtLayout.setError("");
        });
        viewModel.startNextRegister.observe(this, mVoid -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_FirstRegisterFragment_to_SecondRegisterFragment);
        });

        viewModel.emailErrorEvent.observe(this, s -> binding.registerEmailEtLayout.setError(s));

        viewModel.pwErrorEvent.observe(this, s -> {
            binding.registerPwEtLayout.setError(s);
            binding.registerPwCheckEtLayout.setError(s);
        });

        viewModel.viewVerifyCode.observe(this, mVoid -> {
            binding.registerEmailVerifyLayout.setVisibility(View.VISIBLE);
            binding.registerEmailVerifyBtn.setVisibility(View.GONE);
            binding.registerCodeVerifyBtn.setVisibility(View.VISIBLE);
            binding.registerEmailEtLayout.setError("");
        });

        viewModel.confirmVerifyCode.observe(this, mVoid -> {
            binding.registerEmailVerifyLayout.setVisibility(View.GONE);
            binding.registerCodeVerifyBtn.setVisibility(View.GONE);
            binding.registerEmailEt.setFocusable(false);
            binding.registerEmailEt.setClickable(false);
        });

        viewModel.verifyErrorEvent.observe(this, s -> binding.registerEmailVerifyLayout.setError(s));
    }
}