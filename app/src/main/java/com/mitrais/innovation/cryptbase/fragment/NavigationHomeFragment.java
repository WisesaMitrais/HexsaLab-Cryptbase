package com.mitrais.innovation.cryptbase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.activity.ClassicCryptographyActivity;

import java.util.Objects;

public class NavigationHomeFragment extends Fragment {

    /*Declare global variables.*/
    Intent intent;
    Button buttonTryCaesar, buttonTryScytale, buttonTryOneTimePad;
    String transitionName;
    View viewStart;
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
        /*When caesar try button is clicked.*/
        buttonTryCaesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(viewHomeFragment, R.string.tran_name_caesar,
                        R.id.fnh_caesar_card, 1);
            }
        });
        /*When scytale try button is clicked.*/
        buttonTryScytale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(viewHomeFragment, R.string.tran_name_scytale,
                        R.id.fnh_scytale_card, 2);
            }
        });
        /*When one-time pad try button is clicked.*/
        buttonTryOneTimePad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickAction(viewHomeFragment, R.string.tran_name_onetimepad,
                        R.id.fnh_onetimepad_card, 3);
            }
        });
        return viewHomeFragment;
    }

    /**
     * Initialized all components in this activity.
     * @param viewHomeFragment: a view that show on home fragment.
     */
    private void initializeComponents(View viewHomeFragment){
        intent = new Intent(getActivity(), ClassicCryptographyActivity.class);                      //Ordinary Intent for launching a new activity.
        bundle = new Bundle();
        buttonTryCaesar = viewHomeFragment.findViewById(R.id.fnh_caesar_button);
        buttonTryScytale = viewHomeFragment.findViewById(R.id.fnh_scytale_button);
        buttonTryOneTimePad = viewHomeFragment.findViewById(R.id.fnh_onetimepad_button);
    }

    /**
     * Define an action when one of the try button is clicked.
     * @param viewHomeFragment: a view that show on home fragment..
     * @param transitionNameValue: R id from string transition name.
     * @param viewStartValue: R id from string card view.
     * @param activeCardValue: a number that identify active card.
     */
    private void buttonOnClickAction(View viewHomeFragment, int transitionNameValue,
                                     int viewStartValue, int activeCardValue){
        transitionName = getString(transitionNameValue);
        viewStart = viewHomeFragment.findViewById(viewStartValue);                                  //Define the view that the animation will start from.
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        Objects.requireNonNull(getActivity()),
                        viewStart,
                        transitionName
                );
        /*Start the intent.*/
        bundle.putInt("activeCard", activeCardValue);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(Objects.requireNonNull(getContext()), intent,
                options.toBundle());
    }
}
