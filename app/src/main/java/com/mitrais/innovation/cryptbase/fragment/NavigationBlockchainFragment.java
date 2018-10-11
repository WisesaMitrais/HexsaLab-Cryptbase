package com.mitrais.innovation.cryptbase.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitrais.innovation.cryptbase.R;

public class NavigationBlockchainFragment extends Fragment {

    public NavigationBlockchainFragment() {
        //Do nothing.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_blockchain, container, false);
    }
}
