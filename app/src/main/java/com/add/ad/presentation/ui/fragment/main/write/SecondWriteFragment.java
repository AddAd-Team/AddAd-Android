package com.add.ad.presentation.ui.fragment.main.write;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.add.ad.R;
import com.add.ad.databinding.FragmentSecondWriteBinding;
import com.add.ad.presentation.base.BaseFragment;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.util.FileUtil;
import com.add.ad.presentation.viewModel.write.WriteViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

import static android.app.Activity.RESULT_OK;
import static splitties.toast.ToastKt.toast;

@AndroidEntryPoint
public class SecondWriteFragment extends BaseFragment<FragmentSecondWriteBinding, WriteViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_second_write);
        View v = super.onCreateView(inflater, container, savedInstanceState);

        binding.setVm(viewModel);

        return v;
    }

    @Override
    protected Class<WriteViewModel> getViewModelClass() {
        return WriteViewModel.class;
    }

    @Override
    protected ViewModelStoreOwner getVmOwner() {
        return requireActivity();
    }

    @Override
    protected void observeEvent() {
        viewModel.adPrice.setValue("0");

        viewModel.selectImageEvent.observe(this, mVoid -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 200);
        });
        viewModel.clickComplete.observe(this, mVoid -> {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_secondWriteFragment_to_MainFragment);
        });

        viewModel.clickPostDate.observe(this, mVoid -> {
            DatePickerDialog.OnDateSetListener datePickerDialogListener = (datePicker, year, month, day) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                viewModel.calendar.set(year, month, day);
                String dateString = simpleDateFormat.format(viewModel.calendar.getTime());
                viewModel.postEndDate.setValue(dateString);
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(), datePickerDialogListener, viewModel.calendar.get(Calendar.YEAR), viewModel.calendar.get(Calendar.MONTH), viewModel.calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        viewModel.clickAdDate.observe(this, mVoid -> {
            DatePickerDialog.OnDateSetListener datePickerDialogListener = (datePicker, year, month, day) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                viewModel.calendar.set(year, month, day);
                String dateString = simpleDateFormat.format(viewModel.calendar.getTime());
                viewModel.adEndDate.setValue(dateString);
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(), datePickerDialogListener, viewModel.calendar.get(Calendar.YEAR), viewModel.calendar.get(Calendar.MONTH), viewModel.calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
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

            viewModel.adImageUri.setValue(FileUtil.uriToFile(selectedImageUri, requireContext()));
        }
    }
}