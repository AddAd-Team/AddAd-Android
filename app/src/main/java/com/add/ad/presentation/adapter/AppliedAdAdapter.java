package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemAdApplyBinding;
import com.add.ad.entity.response.ResponseApplyInfo;
import com.add.ad.presentation.viewModel.mypage.myad.ApplyAdViewModel;

import java.util.ArrayList;

public class AppliedAdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseApplyInfo> applyItems;
    ApplyAdViewModel applyAdViewModel;

    public AppliedAdAdapter(ArrayList<ResponseApplyInfo> applyItems, ApplyAdViewModel applyAdViewModel) {
        this.applyItems = applyItems;
        this.applyAdViewModel = applyAdViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdApplyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ad_apply, parent, false);
        return new AppliedAdAdapter.AppliedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AppliedAdAdapter.AppliedViewHolder) {
            ((AppliedAdAdapter.AppliedViewHolder) holder).bind(applyItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        applyAdViewModel.appliedResult.setValue(applyItems.size() != 0);
        return applyItems.size();
    }

    public class AppliedViewHolder extends RecyclerView.ViewHolder {
        ItemAdApplyBinding binding;
        ResponseApplyInfo item;
        int position;

        AppliedViewHolder(ItemAdApplyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseApplyInfo item, int position) {
            this.item = item;
            this.position = position;
            binding.setAppliedItem(item);
        }
    }
}
