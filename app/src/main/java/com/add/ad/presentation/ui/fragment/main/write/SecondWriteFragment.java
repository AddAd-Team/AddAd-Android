package com.add.ad.presentation.ui.fragment.main.write;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSecondWriteBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.util.FileUtil;
import com.add.ad.presentation.viewModel.write.WriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static android.app.Activity.RESULT_OK;

@AndroidEntryPoint
public class SecondWriteFragment extends BaseFragment<FragmentSecondWriteBinding> {
    private WriteViewModel writeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_second_write);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        writeViewModel = new ViewModelProvider(requireActivity()).get(WriteViewModel.class);

        binding.setVm(writeViewModel);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        writeViewModel.selectImageEvent.observe(this, mVoid -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 200);
        });
        writeViewModel.clickComplete.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_secondWriteFragment_to_MainFragment);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            binding.secondWriteImage.setImageURI(selectedImageUri);
            binding.secondWritePictureIcon.setVisibility(View.GONE);
            binding.textView14.setVisibility(View.GONE);

            writeViewModel.adImageUri.setValue(FileUtil.uriToFile(selectedImageUri, requireContext()));
        }
    }
}