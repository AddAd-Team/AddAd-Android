package com.add.ad.presentation.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.databinding.FragmentLoginBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.createToast;
import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;
    private TextInputLayout loginEmailErrorLayout;
    private TextInputLayout loginPwErrorLayout;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_login);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginPwErrorLayout = binding.loginPwEtLayout;
        loginEmailErrorLayout = binding.loginEmailEtLayout;

        binding.setVm(loginViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel.startMain.observe(this, mVoid ->
                Navigation.findNavController(requireView()).navigate(R.id.action_LoginInFragment_to_MainFragment));

        loginViewModel.createToastEvent.observe(this, s -> toast(s));
        loginViewModel.pwErrorEvent.observe(this, s -> loginPwErrorLayout.setError(s));
        loginViewModel.idErrorEvent.observe(this, s -> loginEmailErrorLayout.setError(s));
        loginViewModel.startRegister.observe(this, mVoid ->
                Navigation.findNavController(requireView()).navigate(R.id.action_LoginInFragment_to_RegisterFragment));
    }
}