package com.add.ad.presentation.ui.fragment.main.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSearchBinding;
import com.add.ad.presentation.adapter.SearchAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.search.SearchViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private SearchViewModel searchViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_search);

        View v = super.onCreateView(inflater, container, savedInstanceState);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        binding.setVm(searchViewModel);
        searchViewModel.getCreatorList();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchViewModel.searchListEvent.observe(this, mVoid -> {
            binding.searchCreatorRecyclerView.setAdapter(new SearchAdapter(searchViewModel.searchList.getValue(),searchViewModel));
            binding.searchCreatorRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        });
    }
}