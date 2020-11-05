package com.add.ad.presentation.ui.fragment.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSecondRegisterBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.register.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecondRegisterFragment extends BaseFragment<FragmentSecondRegisterBinding> {
    private RegisterViewModel registerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_second_register);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        binding.setVm(registerViewModel);

        if (getArguments() != null) {
            Log.d("userEmail",getArguments().getString("userEmail"));
        }else{
            Log.e("error", String.valueOf(savedInstanceState)+"");
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        getArgs();

        registerViewModel.startLogin.observe(this, mVoid -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_SecondRegisterFragment_to_LoginFragment);
        });
    }

//    private void getArgs(){
//        registerViewModel.userEmail.setValue(getArguments().getString("userEmail"));
//    }
}