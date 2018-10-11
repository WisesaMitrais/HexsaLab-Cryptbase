package com.mitrais.innovation.cryptbase.activity;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.fragment.NavigationBlockchainFragment;
import com.mitrais.innovation.cryptbase.fragment.NavigationHomeFragment;
import com.mitrais.innovation.cryptbase.fragment.NavigationNewsFragment;
import com.mitrais.innovation.cryptbase.utility.dialog.DialogAppInfo;

public class MainActivity extends AppCompatActivity {

    /*Declare global variables.*/
    private Button appInfo;
    private DialogFragment dialogAppInfo;
    private BottomNavigationView bottomNavigation;
    private FragmentTransaction fragmentTransaction;
    private NavigationHomeFragment homeFragment;
    private NavigationNewsFragment webFragment;
    private NavigationBlockchainFragment blockchainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Set potrait screen mode.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        setDialogAppInfo();
        setBottomNavigation();
        setCurrentFragment(homeFragment); //Set home fragment as default fragment.
    }

    /**
    * Initialized all components in this activity.
    */
    private void initializeComponents(){
        appInfo = findViewById(R.id.am_btn_app_info);
        dialogAppInfo = new DialogAppInfo();
        bottomNavigation = findViewById(R.id.am_navigation);
        homeFragment = new NavigationHomeFragment();
        webFragment = new NavigationNewsFragment();
        blockchainFragment = new NavigationBlockchainFragment();
    }

    /**
    * Set a dialog for application info.
    */
    private void setDialogAppInfo(){
        appInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAppInfo.show(getSupportFragmentManager(), "appInfo");
            }
        });
    }

    /**
    * Set bottom navigation bar and it listener for item click action.
    */
    private void setBottomNavigation(){
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /*Fragment selection.*/
                switch (item.getItemId()){
                    case R.id.menu_am_navigation_home:
                        setCurrentFragment(homeFragment);
                        return true;
                    case R.id.menu_am_navigation_web:
                        setCurrentFragment(webFragment);
                        return true;
                    case R.id.menu_am_navigation_blockchain:
                        setCurrentFragment(blockchainFragment);
                        return true;
                }
                return true;
            }
        });
    }

    /**
    * Manage current fragment based on bottom navigation item action which active.
     * @param fragment: a fragment which will set as current fragment.
    */
    private void setCurrentFragment(Fragment fragment){
        //Set animation and new state of fragment.
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
        fragmentTransaction.replace(R.id.am_frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Close main activity if back button presed.
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
