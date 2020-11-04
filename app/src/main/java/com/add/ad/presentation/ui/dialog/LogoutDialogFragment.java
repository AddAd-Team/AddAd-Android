package com.add.ad.presentation.ui.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentLogoutDialogBinding;
import com.add.ad.presentation.base.BaseDialogFragment;
import com.add.ad.presentation.viewModel.mypage.MyPageViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LogoutDialogFragment extends BaseDialogFragment<FragmentLogoutDialogBinding> {
    private MyPageViewModel myPageViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_logout_dialog);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        myPageViewModel = new ViewModelProvider(requireActivity()).get(MyPageViewModel.class);

        binding.setVm(myPageViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myPageViewModel.confirmLogoutEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_LoginFragment);
            dismiss();
        });
        myPageViewModel.cancelLogoutEvent.observe(this, mVoid -> {
            dismiss();
        });
    }
}