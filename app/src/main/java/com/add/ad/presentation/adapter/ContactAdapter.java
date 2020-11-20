package com.add.ad.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.add.ad.R;
import com.add.ad.databinding.ItemContactBinding;
import com.add.ad.databinding.ItemFeedBinding;
import com.add.ad.entity.response.ResponseContactInfo;
import com.add.ad.entity.response.ResponseFeedInfo;
import com.add.ad.presentation.viewModel.contact.ContactViewModel;
import com.add.ad.presentation.viewModel.feed.FeedViewModel;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ResponseContactInfo> contactItems;
    ContactViewModel contactViewModel;

    public ContactAdapter(ArrayList<ResponseContactInfo> contactItems, ContactViewModel contactViewModel) {
        this.contactItems = contactItems;
        this.contactViewModel = contactViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contact, parent, false);
        return new ContactAdapter.ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactAdapter.ContactViewHolder) {
            ((ContactAdapter.ContactViewHolder) holder).bind(contactItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        ItemContactBinding binding;
        ResponseContactInfo item;
        int position;

        ContactViewHolder(ItemContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseContactInfo item, int position) {
            this.item = item;
            this.position = position;
            binding.setContactItem(item);
        }
    }
}
