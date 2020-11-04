package com.add.ad.presentation.ui.fragment.main.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.databinding.FragmentMyPageBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.ui.dialog.LogoutDialogFragment;
import com.add.ad.presentation.viewModel.mypage.MyPageViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> {
    private MyPageViewModel myPageViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_my_page);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        myPageViewModel = new ViewModelProvider(requireActivity()).get(MyPageViewModel.class);

        binding.setVm(myPageViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPageViewModel.myAdEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_MyAdFragment));
        myPageViewModel.myProfileEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_MyProfileFragment));
        myPageViewModel.changePwEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_ChangePasswordFragment));
        myPageViewModel.logoutEvent.observe(this, mVoid ->{
            LogoutDialogFragment logoutDialogFragment = new LogoutDialogFragment();
            logoutDialogFragment.show(requireActivity().getSupportFragmentManager(), "LogoutDialogFragment");
        });
    }
}