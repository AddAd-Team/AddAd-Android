package com.add.ad.presentation.ui.fragment.main.mypage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.add.ad.R;
import com.add.ad.presentation.ui.activity.MainActivity;

public class MyPageFragment extends Fragment {

    ConstraintLayout changePwView;
    ConstraintLayout myProfileView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_page, container, false);
        changePwView = v.findViewById(R.id.my_page_change_pw_view);
        myProfileView = v.findViewById(R.id.my_page_profile_view);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePwView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_ChangePasswordFragment);
            }
        });

        myProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(R.id.action_MainFragment_to_MyProfileFragment);
            }
        });
    }
}