package com.add.ad.presentation.ui.fragment.main.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.databinding.FragmentMyPageBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.ui.dialog.LogoutDialogFragment;
import com.add.ad.presentation.viewModel.mypage.MyPageViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyPageFragment extends BaseFragment<FragmentMyPageBinding, MyPageViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_my_page);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);
        viewModel.getProfile();

        return v;
    }

    @Override
    protected Class<MyPageViewModel> getViewModelClass() {
        return MyPageViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.adLikeEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_AdLikeFragment));
        viewModel.myAdEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_MyAdFragment));
        viewModel.myProfileEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_MyProfileFragment));
        viewModel.changePwEvent.observe(this, mVoid -> Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_ChangePasswordFragment));
        viewModel.logoutEvent.observe(this, mVoid -> {
            LogoutDialogFragment logoutDialogFragment = new LogoutDialogFragment();
            logoutDialogFragment.show(requireActivity().getSupportFragmentManager(), "LogoutDialogFragment");
        });
    }
}