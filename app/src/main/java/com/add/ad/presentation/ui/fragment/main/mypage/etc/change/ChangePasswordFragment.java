package com.add.ad.presentation.ui.fragment.main.mypage.etc.change;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.add.ad.R;
import com.add.ad.databinding.FragmentChangePasswordBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.ChangePasswordViewModel;
import com.add.ad.presentation.viewModel.MyPageViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class ChangePasswordFragment extends BaseFragment<FragmentChangePasswordBinding> {
    private ChangePasswordViewModel changePasswordViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_change_password);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        changePasswordViewModel = new ViewModelProvider(requireActivity()).get(ChangePasswordViewModel.class);

        binding.setVm(changePasswordViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePasswordViewModel.pwChangeEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_changePasswordFragment_to_mainFragment);
        });
        changePasswordViewModel.createToastEvent.observe(this, s -> toast(s));
    }
}