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
import com.add.ad.presentation.viewModel.register.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class FirstRegisterFragment extends BaseFragment<FragmentFirstRegisterBinding> {

    private RegisterViewModel registerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_first_register);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        binding.setVm(registerViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel.clearErrorEvent.observe(this, mVoid -> {
            binding.registerEmailEtLayout.setError("");
            binding.registerPwCheckEtLayout.setError("");
            binding.registerPwEtLayout.setError("");
        });
        registerViewModel.startNextRegister.observe(this, mVoid -> {
                    Fragment firstRegisterFragment = new FirstRegisterFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("userEmail", registerViewModel.userEmail.getValue());
                    bundle.putString("userPw", registerViewModel.userPassword.getValue());
                    firstRegisterFragment.setArguments(bundle);

                    Navigation.findNavController(requireView()).navigate(R.id.action_FirstRegisterFragment_to_SecondRegisterFragment);
                }
        );
        registerViewModel.emailErrorEvent.observe(this, s -> binding.registerEmailEtLayout.setError(s));

        registerViewModel.pwErrorEvent.observe(this, s -> {
            binding.registerPwEtLayout.setError(s);
            binding.registerPwCheckEtLayout.setError(s);
        });

        registerViewModel.viewVerifyCode.observe(this, mVoid -> {
            binding.registerEmailVerifyLayout.setVisibility(View.VISIBLE);
            binding.registerEmailVerifyBtn.setVisibility(View.GONE);
            binding.registerCodeVerifyBtn.setVisibility(View.VISIBLE);
            binding.registerEmailEtLayout.setError("");
        });

        registerViewModel.confirmVerifyCode.observe(this, mVoid -> {
            binding.registerEmailVerifyLayout.setVisibility(View.GONE);
            binding.registerCodeVerifyBtn.setVisibility(View.GONE);
            binding.registerEmailEt.setFocusable(false);
            binding.registerEmailEt.setClickable(false);
        });

        registerViewModel.createToastEvent.observe(this, s -> toast(s));
        registerViewModel.verifyErrorEvent.observe(this, s -> binding.registerEmailVerifyLayout.setError(s));
    }
}