package com.mitrais.innovation.cryptbase.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitrais.innovation.cryptbase.R;

public class NavigationNewsFragment extends Fragment {

    /**
     * Class constructor.
     */
    public NavigationNewsFragment() {
        //Do nothing.
    }

    /**
     * OnCreate method.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * OnCreateView method.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_web, container, false);
    }
}
