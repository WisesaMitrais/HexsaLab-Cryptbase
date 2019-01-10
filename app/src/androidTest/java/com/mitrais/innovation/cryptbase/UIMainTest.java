package com.mitrais.innovation.cryptbase;

import com.mitrais.innovation.cryptbase.activity.SplashScreenActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(JUnit4.class)
public class UIMainTest {

    /**
     * Launch splash screen activity before test.
     */
    @Rule
    public ActivityTestRule<SplashScreenActivity> activityTestRule =
            new ActivityTestRule<>(SplashScreenActivity.class);

    /**
     * Testing main screens in Cryptbase.
     */
    @Test
    public void testMainScreesInCryptbase(){
        /* Data prepare. */
        String[] dataString = {"News", "Blockchain", "Home"};
        int dataID = R.id.am_btn_app_info;
        wait(3000);
        /* Process for each button */
        for(int idx = 0; idx <= dataString.length; idx++){
            if(idx == 3){
                wait(500);
                onView(allOf(withId(dataID), isDisplayed())).perform(click());
                wait(500);
                onView(allOf(withText("OK"), isDisplayed())).perform(click());
            }else{
                wait(500);
                onView(allOf(withText(dataString[idx]), isDisplayed())).perform(click());
            }
        }
    }

    /**
     * Testing try button for each algorithm.
     */
    @Test
    public void testTryButtonInAlgorithmDetail(){
        /* Data prepare. */
        int[] dataButton = {R.id.fnh_caesar_button, R.id.fnh_scytale_button,
                            R.id.fnh_onetimepad_button, R.id.fnh_des_button,
                            R.id.fnh_aes_button, R.id.fnh_rc_button,
                            R.id.fnh_rsa_button, R.id.fnh_elgamal_button,
                            R.id.fnh_ec_button, R.id.fnh_md_button,
                            R.id.fnh_sha_button, R.id.fnh_hmac_button,
                            R.id.fnh_ds_button, R.id.fnh_stega_button};
        wait(3000);
        /* Process for each button */
        for(int idx = 0; idx < 14; idx++){
            wait(500);
            onView(withId(dataButton[idx])).perform(scrollTo(), click());
            wait(500);
            onView(allOf(withId(R.id.dad_button_try), isDisplayed())).perform(click());
            wait(500);
            pressBack();
            wait(500);
            onView(allOf(withId(R.id.dad_header_title), isDisplayed())).perform(swipeDown());
        }
    }

    /**
     * Set waiting time.
     * @param time: time unit (miliseconds).
     */
    private void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
