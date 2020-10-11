package com.add.ad.presentation.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.add.ad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {

    BottomNavigationView main_navigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        main_navigation = (BottomNavigationView) v.findViewById(R.id.main_navigation);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        main_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.feed_fragment:
                    return selectFeed();
                case R.id.search_fragment:
                    return selectSearch();
                case R.id.write_fragment:
                    return selectWrite();
                case R.id.contact_fragment:
                    return selectContact();
                case R.id.mypage_fragment:
                    return selectMyPage();
            }
            return false;
        }
    };

    private void replaceFragment(int action) {
        
    }

    private Boolean selectFeed() {
        replaceFragment(R.id.action_global_FeedFragment);
        return true;
    }

    private Boolean selectSearch() {
        replaceFragment(R.id.action_global_SearchFragment);
        return true;
    }

    private Boolean selectWrite() {
        replaceFragment(R.id.action_global_WriteFragment);
        return true;
    }

    private Boolean selectContact() {
        replaceFragment(R.id.action_global_ContactFragment);
        return true;
    }

    private Boolean selectMyPage() {
        replaceFragment(R.id.action_global_MyPageFragment);
        return true;
    }
}