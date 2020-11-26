package com.add.ad.presentation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemAdAccessBinding;
import com.add.ad.databinding.ItemAdApplyBinding;
import com.add.ad.entity.response.ResponseApplyInfo;
import com.add.ad.presentation.viewModel.mypage.myad.ApplyAdViewModel;

import java.util.ArrayList;

public class AccessAdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseApplyInfo> accessItems = new ArrayList<>(10);
    ApplyAdViewModel applyAdViewModel;
    AppliedAdAdapter appliedAdAdapter;

    public void setAppliedAdAdapter(AppliedAdAdapter appliedAdAdapter) {
        this.appliedAdAdapter = appliedAdAdapter;
    }

    public AccessAdAdapter(ApplyAdViewModel applyAdViewModel) {
        this.applyAdViewModel = applyAdViewModel;
    }

    public void moveToApply(ResponseApplyInfo item){
        appliedAdAdapter.add(item);
        accessItems.remove(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdAccessBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ad_access, parent, false);
        return new AccessAdAdapter.AccessViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AccessAdAdapter.AccessViewHolder) {
            ((AccessAdAdapter.AccessViewHolder) holder).bind(accessItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        applyAdViewModel.accessResult.setValue(accessItems.size() != 0);
        return accessItems.size();
    }

    public void add(ResponseApplyInfo data){
        accessItems.add(data);
        notifyDataSetChanged();
    }

    public class AccessViewHolder extends RecyclerView.ViewHolder {
        ItemAdAccessBinding binding;
        ResponseApplyInfo item;
        int position;

        AccessViewHolder(ItemAdAccessBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseApplyInfo item, int position) {
            this.item = item;
            this.position = position;
            binding.setAppliedItem(item);
            binding.setVh(this);
        }
        public void onClickCancel(View v){
            moveToApply(item);
        }
    }
}

