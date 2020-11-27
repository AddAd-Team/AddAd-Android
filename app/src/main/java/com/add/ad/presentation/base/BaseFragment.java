package com.add.ad.presentation.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.add.ad.R;
import com.add.ad.presentation.util.ProgressDialogUtil;
import com.google.android.material.snackbar.Snackbar;

import static splitties.toast.ToastKt.toast;

public abstract class BaseFragment<T extends ViewDataBinding, E extends BaseViewModel> extends Fragment {
    private int layoutId;
    protected T binding;
    protected E viewModel;
    private ProgressDialogUtil progressDialogUtil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        viewModel = new ViewModelProvider(getVmOwner()).get(getViewModelClass());

        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm, viewModel);

        progressDialogUtil = new ProgressDialogUtil(getContext());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observeEvent();

        viewModel.createToastEvent.observe(this, it -> toast(it));
        viewModel.createSnackEvent.observe(this, it -> {
            Snackbar snackbar;
            snackbar = Snackbar.make(view, it, Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.parseColor("#4CD653"));
            snackbar.show();
        } );
        viewModel.createProgressEvent.observe(this, mVoid -> progressDialogUtil.show());
        viewModel.dismissProgressEvent.observe(this, mVoid -> progressDialogUtil.dismiss());
        viewModel.backEvent.observe(this, mVoid -> Navigation.findNavController(requireView()).popBackStack());
    }

    public void setLayout(int layoutId) {
        this.layoutId = layoutId;
    }

    protected abstract Class<E> getViewModelClass();

    protected abstract ViewModelStoreOwner getVmOwner();

    protected abstract void observeEvent();

}
