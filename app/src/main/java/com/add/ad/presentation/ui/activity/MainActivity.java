package com.add.ad.presentation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.add.ad.R;

import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}