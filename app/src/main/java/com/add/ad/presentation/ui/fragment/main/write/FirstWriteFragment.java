package com.add.ad.presentation.ui.fragment.main.write;

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
import com.add.ad.databinding.FragmentFirstWriteBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.RegisterViewModel;
import com.add.ad.presentation.viewModel.WriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FirstWriteFragment extends BaseFragment<FragmentFirstWriteBinding> {
    private WriteViewModel writeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_first_write);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        writeViewModel = new ViewModelProvider(requireActivity()).get(WriteViewModel.class);

        binding.setVm(writeViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        writeViewModel.clickNextEvent.observe(this, mVoid ->
                Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_SecondWriteFragment));
    }
}