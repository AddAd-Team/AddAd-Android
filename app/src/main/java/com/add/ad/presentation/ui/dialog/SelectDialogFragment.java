package com.add.ad.presentation.ui.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSelectDialogBinding;
import com.add.ad.presentation.base.BaseDialogFragment;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.mypage.MyPageViewModel;
import com.add.ad.presentation.viewModel.mypage.myad.MyAdViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SelectDialogFragment extends BaseDialogFragment<FragmentSelectDialogBinding> {
    private MyAdViewModel myAdViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_select_dialog);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        myAdViewModel = new ViewModelProvider(requireActivity()).get(MyAdViewModel.class);

        binding.setVm(myAdViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myAdViewModel.clickApplyListEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_myAdFragment_to_AdApplyFragment);
            dismiss();
        });
        myAdViewModel.clickEditEvent.observe(this, mVoid -> {
            Log.d("clickEditEvent", "ckjcmckcjlk");
        });
    }
}