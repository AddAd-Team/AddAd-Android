package com.add.ad.presentation.ui.fragment.landing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.add.ad.R;

public class SecondLandingFragment extends Fragment {

    Button secondNextBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_second_landing, container, false);
        secondNextBtn = (Button) v.findViewById(R.id.go_third_landing_btn);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        secondNextBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_SecondLandingFragment_to_ThirdLandingFragment);
            }
        });
    }
}