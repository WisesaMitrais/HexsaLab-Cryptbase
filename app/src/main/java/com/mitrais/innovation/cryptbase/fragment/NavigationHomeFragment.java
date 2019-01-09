package com.mitrais.innovation.cryptbase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mitrais.innovation.cryptbase.R;

public class NavigationHomeFragment extends Fragment {

    /* Global variables. */
    private Intent intent;
    private Button bDetailCaesar, bDetailScytale, bDetailOTP, bDetailDES, bDetailAES, bDetailRC;
    private Button bDetailRSA, bDetailElgamal, bDetailEC, bDetailMD, bDetailSHA, bDetailHMAC;
    private Button bDetailDS, bDetailStega;
    Bundle bundle;

    /**
     * Create constructor which do nothing.
     */
    public NavigationHomeFragment() {
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
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewHomeFragment = inflater.inflate(R.layout.fragment_navigation_home, container,
                false);
        initializeComponents(viewHomeFragment);
        /* Caesar detail button. */
        bDetailCaesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(1);
            }
        });
        /* Scytale detail button. */
        bDetailScytale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(2);
            }
        });
        /* One-time pad detail button. */
        bDetailOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(3);
            }
        });
        /* DES detail button. */
        bDetailDES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(4);
            }
        });
        /* AES detail button. */
        bDetailAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(5);
            }
        });
        /* Rivest cipher detail button. */
        bDetailRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(6);
            }
        });
        /* RSA detail button. */
        bDetailRSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(7);
            }
        });
        /* Elgamal detail button. */
        bDetailElgamal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(8);
            }
        });
        /* Elliptic curve detail button. */
        bDetailEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(9);
            }
        });
        /* Message digest detail button. */
        bDetailMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(10);
            }
        });
        /* SHA detail button. */
        bDetailSHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(11);
            }
        });
        /* HMAC detail button. */
        bDetailHMAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(12);
            }
        });
        /* Digital signature detail button. */
        bDetailDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(13);
            }
        });
        /* Steganography detail button. */
        bDetailStega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(14);
            }
        });
        return viewHomeFragment;
    }

    /**
     * Initialized all components in this activity.
     * @param viewHomeFragment: a view that show on home fragment.
     */
    private void initializeComponents(View viewHomeFragment){
        bundle = new Bundle();
        bDetailCaesar = viewHomeFragment.findViewById(R.id.fnh_caesar_button);
        bDetailScytale = viewHomeFragment.findViewById(R.id.fnh_scytale_button);
        bDetailOTP = viewHomeFragment.findViewById(R.id.fnh_onetimepad_button);
        bDetailDES = viewHomeFragment.findViewById(R.id.fnh_des_button);
        bDetailAES = viewHomeFragment.findViewById(R.id.fnh_aes_button);
        bDetailRC = viewHomeFragment.findViewById(R.id.fnh_rc_button);
        bDetailRSA = viewHomeFragment.findViewById(R.id.fnh_rsa_button);
        bDetailElgamal = viewHomeFragment.findViewById(R.id.fnh_elgamal_button);
        bDetailEC = viewHomeFragment.findViewById(R.id.fnh_ec_button);
        bDetailMD = viewHomeFragment.findViewById(R.id.fnh_md_button);
        bDetailSHA = viewHomeFragment.findViewById(R.id.fnh_sha_button);
        bDetailHMAC = viewHomeFragment.findViewById(R.id.fnh_hmac_button);
        bDetailDS = viewHomeFragment.findViewById(R.id.fnh_ds_button);
        bDetailStega = viewHomeFragment.findViewById(R.id.fnh_stega_button);
    }

    /**
     * Define an action when one of the try button is clicked.
     * @param activeCardValue: a number that identify active card.
     */
    private void buttonOnClickAction(int activeCardValue){
        BottomSheetDialogFragment bottomSheet = new BottomSheetAlgorithmDetailFragment();
        bundle.putInt("activeCard", activeCardValue);
        bottomSheet.setArguments(bundle);
        bottomSheet.show(getFragmentManager(), bottomSheet.getTag());
    }
}
