package com.add.ad.presentation.ui.fragment.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.data.local.SharedPref;
import com.add.ad.databinding.FragmentSplashBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.splash.SplashViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashFragment extends BaseFragment<FragmentSplashBinding, SplashViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_splash);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(() -> viewModel.startApp(),3000);

        return v;
    }

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.firstLandingEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_FirstLandingFragment);
        });
        viewModel.loginEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment);
        });
    }

}