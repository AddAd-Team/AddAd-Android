package com.add.ad.presentation.ui.fragment.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFirstRegisterBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.LoginViewModel;
import com.add.ad.presentation.viewModel.RegisterViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FirstRegisterFragment extends BaseFragment<FragmentFirstRegisterBinding> {

    private RegisterViewModel registerViewModel;
    private TextInputLayout registerEmailErrorLayout;
    private TextInputLayout registerVerifyCodeErrorLayout;
    private TextInputLayout registerPwErrorLayout;
    private TextInputLayout registerPwCheckErrorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_first_register);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerEmailErrorLayout = binding.registerEmailEtLayout;
        registerVerifyCodeErrorLayout = binding.registerEmailVerifyLayout;
        registerPwErrorLayout = binding.registerPwEtLayout;
        registerPwCheckErrorLayout = binding.registerPwCheckEtLayout;

        binding.setVm(registerViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel.startNextRegister.observe(this, mVoid ->
                Navigation.findNavController(requireView()).navigate(R.id.action_FirstRegisterFragment_to_SecondRegisterFragment));
        registerViewModel.emailErrorEvent.observe(this, s -> registerEmailErrorLayout.setError(s));
        registerViewModel.pwErrorEvent.observe(this, s -> {
            registerPwErrorLayout.setError(s);
            registerPwCheckErrorLayout.setError(s);
        });
    }
}