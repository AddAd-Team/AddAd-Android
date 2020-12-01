package com.add.ad.presentation.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.add.ad.R;
import com.add.ad.data.local.SharedPref;
import com.google.firebase.iid.FirebaseInstanceId;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPref sharedPref = new SharedPref(this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                    }
                    String token = task.getResult().getToken();
                    sharedPref.saveDeviceToken(token);
                });
    }
}