package com.add.ad.presentation.ui.fragment.main.write;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentFirstWriteBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.viewModel.write.WriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FirstWriteFragment extends BaseFragment<FragmentFirstWriteBinding, WriteViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_first_write);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);

        return v;
    }

    @Override
    protected Class<WriteViewModel> getViewModelClass() {
        return WriteViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.clickNextEvent.observe(this, mVoid ->
                Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_SecondWriteFragment));
    }
}