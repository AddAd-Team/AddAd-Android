package com.add.ad.presentation.ui.fragment.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.databinding.FragmentLoginBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.util.ProgressDialogUtil;
import com.add.ad.presentation.viewModel.login.LoginViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.createToast;
import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_login);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        viewModel.autoLogin();

        return v;
    }

    @Override
    protected Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.loginMediator.addSource(viewModel.userId, value -> {
            viewModel.loginMediator.setValue(checkFullText());
        });
        viewModel.loginMediator.addSource(viewModel.userPassword, value -> {
            viewModel.loginMediator.setValue(checkFullText());
        });

        viewModel.startMain.observe(this, mVoid ->
                Navigation.findNavController(requireView()).navigate(R.id.action_LoginInFragment_to_MainFragment));

        viewModel.pwErrorEvent.observe(this, s -> binding.loginPwEtLayout.setError(s));

        viewModel.startRegister.observe(this, mVoid ->
                Navigation.findNavController(requireView()).navigate(R.id.action_LoginInFragment_to_RegisterFragment));

    }
    private boolean checkFullText() {
        return !viewModel.userId.getValue().isEmpty() && !viewModel.userPassword.getValue().isEmpty();
    }
}