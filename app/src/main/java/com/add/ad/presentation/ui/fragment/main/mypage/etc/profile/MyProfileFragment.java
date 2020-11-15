package com.add.ad.presentation.ui.fragment.main.mypage.etc.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.databinding.FragmentMyPageBinding;
import com.add.ad.databinding.FragmentMyProfileBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.util.FileUtil;
import com.add.ad.presentation.viewModel.mypage.MyPageViewModel;
import com.add.ad.presentation.viewModel.mypage.profile.ProfileViewModel;
import com.add.ad.presentation.viewModel.write.WriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

import static android.app.Activity.RESULT_OK;
import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class MyProfileFragment extends BaseFragment<FragmentMyProfileBinding, ProfileViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_my_profile);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);

        viewModel.getMyProfile();
        viewModel.isEdit.setValue(false);

        return v;
    }

    @Override
    protected Class<ProfileViewModel> getViewModelClass() {
        return ProfileViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return this;
    }

    @Override
    protected void observeEvent() {
        viewModel.profileEditEvent.observe(this, mVoid -> {
            binding.constraintLayout4.setVisibility(View.VISIBLE);
            binding.textView24.setVisibility(View.VISIBLE);
            binding.myProfileUserNameEt.setVisibility(View.VISIBLE);
            binding.myProfileUserNameTv.setVisibility(View.INVISIBLE);
            binding.myProfileTagEt.setVisibility(View.VISIBLE);
            binding.myProfileTagTv.setVisibility(View.INVISIBLE);
            binding.myProfileIntroduceEt.setVisibility(View.VISIBLE);
            binding.myProfileIntroduceTv.setVisibility(View.INVISIBLE);
            binding.myProfileEditTv.setText("완료");

            binding.myProfileUserNameEt.setText(viewModel.userProfile.getValue().getUserName());
            binding.myProfileIntroduceEt.setText(viewModel.userProfile.getValue().getUserDescription());
            binding.myProfileTagEt.setText(viewModel.userProfile.getValue().getUserTag());
        });

        viewModel.profileEditCompleteEvent.observe(this, mVoid ->{
            binding.constraintLayout4.setVisibility(View.INVISIBLE);
            binding.textView24.setVisibility(View.INVISIBLE);
            binding.myProfileUserNameEt.setVisibility(View.INVISIBLE);
            binding.myProfileUserNameTv.setVisibility(View.VISIBLE);
            binding.myProfileTagEt.setVisibility(View.INVISIBLE);
            binding.myProfileTagTv.setVisibility(View.VISIBLE);
            binding.myProfileIntroduceEt.setVisibility(View.INVISIBLE);
            binding.myProfileIntroduceTv.setVisibility(View.VISIBLE);
            binding.myProfileEditTv.setText("다음");

            viewModel.getMyProfile();
        });

        viewModel.profileImageEditEvent.observe(this, mVoid -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 200);
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            binding.myPageProfileImage.setImageURI(selectedImageUri);

            viewModel.profileImageUri.setValue(FileUtil.uriToFile(selectedImageUri, requireContext()));
        }
    }
}