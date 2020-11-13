package com.add.ad.presentation.ui.fragment.main.mypage.etc.visit;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentVisitProfileBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.search.SearchViewModel;
import com.add.ad.presentation.viewModel.write.WriteViewModel;

public class VisitProfileFragment extends BaseFragment<FragmentVisitProfileBinding> {
    private SearchViewModel searchViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_visit_profile);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        binding.setVm(searchViewModel);

        return v;
    }
}