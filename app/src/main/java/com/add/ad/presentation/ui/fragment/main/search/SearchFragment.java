package com.add.ad.presentation.ui.fragment.main.search;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSearchBinding;
import com.add.ad.presentation.adapter.SearchAdapter;
import com.add.ad.presentation.adapter.SearchTagAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.util.TagList;
import com.add.ad.presentation.viewModel.search.SearchViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_search);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        viewModel.getCreatorList();

        return v;
    }

    @Override
    protected Class<SearchViewModel> getViewModelClass() {
        return SearchViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void observeEvent() {
        viewModel.searchListEvent.observe(this, mVoid -> {
            binding.searchCreatorRecyclerView.setAdapter(new SearchAdapter(viewModel.searchList.getValue(), viewModel));
            binding.searchCreatorRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            binding.searchCreatorTagRecyclerView.setAdapter(new SearchTagAdapter(TagList.getTagList(), viewModel));
            binding.searchCreatorTagRecyclerView.setLayoutManager(horizontalLayoutManager);
        });

        binding.searchCreatorEt.setOnTouchListener((view1, motionEvent) -> {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_search);

            final boolean isRight = motionEvent.getX() >= binding.searchCreatorEt.getWidth() - drawable.getIntrinsicWidth() - 100;
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && isRight) {
                viewModel.searchCreator();
            }
            return false;
        });

        viewModel.searchDetailEvent.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_MainFragment_to_VisitProfileFragment);
        });
    }
}