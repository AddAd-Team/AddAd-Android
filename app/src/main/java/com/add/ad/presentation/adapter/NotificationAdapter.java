package com.add.ad.presentation.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemAlertAdvertiserBinding;
import com.add.ad.databinding.ItemAlertCreatorBinding;
import com.add.ad.entity.response.ResponseAlertInfo;
import com.add.ad.presentation.viewModel.mypage.alert.AlertViewModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseAlertInfo> alertItems;
    AlertViewModel alertViewModel;

    public NotificationAdapter(ArrayList<ResponseAlertInfo> alertItems, AlertViewModel alertViewModel) {
        this.alertItems = alertItems;
        this.alertViewModel = alertViewModel;
    }

    @Override
    public int getItemViewType(int position) {
        if (alertViewModel.getUserInfo()) {
            return 1;
        } else return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            ItemAlertCreatorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_alert_creator, parent, false);
            return new AlertCreatorHolder(binding);
        } else {
            ItemAlertAdvertiserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_alert_advertiser, parent, false);
            return new AlertAdvertiserHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NotificationAdapter.AlertCreatorHolder) {
            ((NotificationAdapter.AlertCreatorHolder) holder).bind(alertItems.get(position));
        } else if (holder instanceof NotificationAdapter.AlertAdvertiserHolder) {
            ((NotificationAdapter.AlertAdvertiserHolder) holder).bind(alertItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return alertItems.size();
    }

    public class AlertCreatorHolder extends RecyclerView.ViewHolder {
        ItemAlertCreatorBinding binding;
        ResponseAlertInfo item;

        AlertCreatorHolder(ItemAlertCreatorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseAlertInfo item) {
            this.item = item;
            binding.setAlertItem(item);
        }
    }

    public class AlertAdvertiserHolder extends RecyclerView.ViewHolder {
        ItemAlertAdvertiserBinding binding;
        ResponseAlertInfo item;

        AlertAdvertiserHolder(ItemAlertAdvertiserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseAlertInfo item) {
            this.item = item;
            binding.setAlertItem(item);
        }
    }
}
