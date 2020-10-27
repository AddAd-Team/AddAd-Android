package com.add.ad.presentation.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.databinding.FragmentLoginBinding;
import com.add.ad.presentation.viewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.createToast;
import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private TextInputLayout loginEmailErrorLayout;
    private TextInputLayout loginPwErrorLayout;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);

        loginPwErrorLayout = binding.loginPwEtLayout;
        loginEmailErrorLayout = binding.loginEmailEtLayout;

        binding.setLifecycleOwner(this);
        binding.setVm(loginViewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel.startMain.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                Navigation.findNavController(getView()).navigate(R.id.action_LoginInFragment_to_MainFragment);
            }
        });

        loginViewModel.createToastEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                toast(s);
            }
        });

        loginViewModel.pwErrorEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loginPwErrorLayout.setError(s);
            }
        });
        loginViewModel.idErrorEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loginEmailErrorLayout.setError(s);
            }
        });
    }
}