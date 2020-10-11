package com.add.ad.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.add.ad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {

    BottomNavigationView main_navigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        NavController navController = Navigation.findNavController(v.findViewById(R.id.main_container));

        main_navigation = (BottomNavigationView) v.findViewById(R.id.main_navigation);
        NavigationUI.setupWithNavController(main_navigation,navController);

        return v;
    }
}