package com.add.ad.presentation.ui.fragment.main.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.add.ad.R;
import com.add.ad.databinding.FragmentContactBinding;
import com.add.ad.presentation.adapter.ContactAdapter;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.viewModel.contact.ContactViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactFragment extends BaseFragment<FragmentContactBinding, ContactViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_contact);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        viewModel.getContactList();

        return v;
    }

    @Override
    protected Class<ContactViewModel> getViewModelClass() {
        return ContactViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.contactEvent.observe(this, mVoid -> {
            binding.contactRecyclerView.setAdapter(new ContactAdapter(viewModel.contactList.getValue(), viewModel));
            binding.contactRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

            binding.contactProgressBar.setVisibility(View.GONE);
        });
    }
}