package com.mitrais.innovation.cryptbase.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.activity.TryAppliedAlgorithmActivity;
import com.mitrais.innovation.cryptbase.activity.TryClassicAlgorithmActivity;
import com.mitrais.innovation.cryptbase.activity.TryHashAlgorithmActivity;
import com.mitrais.innovation.cryptbase.activity.TryModernAlgorithmActivity;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandBold;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandMedium;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandRegular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AlgorithmDetailData{

    /* Global static variables. */
    public static final int[] HEADER_IMAGE = {R.drawable.img_caesar, R.drawable.img_scytale,
            R.drawable.img_onetimepad, R.drawable.img_des,
            R.drawable.img_aes, R.drawable.img_rc,
            R.drawable.img_rsa, R.drawable.img_elgamal,
            R.drawable.img_ellipticcurve, R.drawable.img_md,
            R.drawable.img_sha, R.drawable.img_hmac,
            R.drawable.img_signature, R.drawable.img_stegano};
    public static final int[] HEADER_BACKGROUND = {R.drawable.custom_round_gradient_color_red1,
            R.drawable.custom_round_gradient_color_red1,
            R.drawable.custom_round_gradient_color_red1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_blue1,
            R.drawable.custom_round_gradient_color_green1,
            R.drawable.custom_round_gradient_color_green1,
            R.drawable.custom_round_gradient_color_green1,
            R.drawable.custom_round_gradient_color_yellow1,
            R.drawable.custom_round_gradient_color_yellow1};
    public static final int[] HEADER_TITLE = {R.string.head_name_caesar, R.string.head_name_scytale,
            R.string.head_name_onetimepad, R.string.head_name_des,
            R.string.head_name_aes, R.string.head_name_rc,
            R.string.head_name_rsa, R.string.head_name_elgamal,
            R.string.head_name_ec, R.string.head_name_md,
            R.string.head_name_sha, R.string.head_name_hmac,
            R.string.head_name_ds, R.string.head_name_stega};
    public static final int[] BODY_TITLE = {R.string.body_title_caesar, R.string.body_title_scytale,
            R.string.body_title_onetimepad, R.string.body_title_des,
            R.string.body_title_aes, R.string.body_title_rc,
            R.string.body_title_rsa, R.string.body_title_elgamal,
            R.string.body_title_ec, R.string.body_title_md,
            R.string.body_title_sha, R.string.body_title_hmac,
            R.string.body_title_ds, R.string.body_title_stega};
    public static final int[] BODY_DESC = {R.string.body_desc_caesar, R.string.body_desc_scytale,
            R.string.body_desc_onetimepad, R.string.body_desc_des,
            R.string.body_desc_aes, R.string.body_desc_rc,
            R.string.body_desc_rsa, R.string.body_desc_elgamal,
            R.string.body_desc_ec, R.string.body_desc_md,
            R.string.body_desc_sha, R.string.body_desc_hmac,
            R.string.body_desc_ds, R.string.body_desc_stega};
    public static final int[] TEXT_LINK_1 = {R.string.caesar_link_1, R.string.scytale_link_1,
            R.string.otp_link_1, R.string.des_link_1,
            R.string.aes_link_1, R.string.rc_link_1,
            R.string.rsa_link_1, R.string.elgamal_link_1,
            R.string.ec_link_1, R.string.md_link_1,
            R.string.sha_link_1, R.string.hmac_link_1,
            R.string.ds_link_1, R.string.stega_link_1};
    public static final int[] TEXT_LINK_2 = {R.string.caesar_link_2, R.string.scytale_link_2,
            R.string.otp_link_2, R.string.des_link_2,
            R.string.aes_link_2, R.string.rc_link_2,
            R.string.rsa_link_2, R.string.elgamal_link_2,
            R.string.ec_link_2, R.string.md_link_2,
            R.string.sha_link_2, R.string.hmac_link_2,
            R.string.ds_link_2, R.string.stega_link_2};
}

public class BottomSheetAlgorithmDetailFragment extends BottomSheetDialogFragment {

    /* Global variables. */
    private ImageView headerImage;
    private LinearLayout headerBackground;
    private FontQuicksandBold headerTitle;
    private FontQuicksandMedium bodyTitle, bodyDesc;
    private FontQuicksandRegular textLink1, textLink2;
    private int cardValue;
    private String tempString;
    private Button buttonTry;

    /**
     * OnCreate method.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        cardValue = bundle.getInt("activeCard");
    }

    /**
     * setupDialog method.
     */
    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.dialog_algorithm_detail, null);
        dialog.setContentView(contentView);
        initalizedComponents(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        /* Checking fragment communication. */
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    /**
     * Initialized components.
     * @param view: fragment view of modal bottom sheet.
     */
    private void initalizedComponents(View view){
        headerImage = view.findViewById(R.id.dad_header_image);
        headerBackground = view.findViewById(R.id.dad_header_background);
        headerTitle = view.findViewById(R.id.dad_header_title);
        bodyTitle = view.findViewById(R.id.dad_body_title);
        bodyDesc = view.findViewById(R.id.dad_body_desc);
        textLink1 = view.findViewById(R.id.dad_link_1);
        textLink2 = view.findViewById(R.id.dad_link_2);
        setContent(cardValue);
        buttonTry = view.findViewById(R.id.dad_button_try);
        setButtonAction();
    }

    /**
     * Set data content in modal bottom sheet for algorithm detail.
     * @param cardValue: value of active card at home fragment.
     */
    private void setContent(int cardValue){
        headerImage.setImageResource(AlgorithmDetailData.HEADER_IMAGE[cardValue - 1]);
        headerBackground.setBackgroundResource(AlgorithmDetailData.HEADER_BACKGROUND[cardValue - 1]);
        headerTitle.setText(AlgorithmDetailData.HEADER_TITLE[cardValue - 1]);
        bodyTitle.setText(AlgorithmDetailData.BODY_TITLE[cardValue - 1]);
        bodyDesc.setText(AlgorithmDetailData.BODY_DESC[cardValue - 1]);
        /* Set text link 1 and 2. */
        tempString = getString(AlgorithmDetailData.TEXT_LINK_1[cardValue - 1]);
        tempString = extractStringFromURL(tempString);
        textLink1.setText(tempString);
        tempString = getString(AlgorithmDetailData.TEXT_LINK_2[cardValue - 1]);
        tempString = extractStringFromURL(tempString);
        textLink2.setText(tempString);
        setOnClickTextLink();
    }

    /**
     * Set data content in modal bottom sheet for algorithm detail.
     * @param url: URL string.
     * @return a substring from URL.
     */
    private String extractStringFromURL(String url){
        String result = "";
        Pattern pattern = Pattern.compile("//(.*?)/");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * Set action when text link clicked.
     */
    private void setOnClickTextLink(){
        textLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTextLink(getString(AlgorithmDetailData.TEXT_LINK_1[cardValue - 1]));
            }
        });
        textLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTextLink(getString(AlgorithmDetailData.TEXT_LINK_2[cardValue - 1]));
            }
        });
    }

    /**
     * Open text link with activity.
     * @param url: url string.
     */
    private void openTextLink(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    /**
     * Set action when try button clicked.
     */
    private void setButtonAction(){
        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(cardValue >= 1 && cardValue <=3){ // Classic algorithms.
                    intent = new Intent(getActivity(), TryClassicAlgorithmActivity.class);
                }else if(cardValue >= 4 && cardValue <= 9){ // Modern algorithms.
                    intent = new Intent(getActivity(), TryModernAlgorithmActivity.class);
                }else if(cardValue >= 10 && cardValue <= 12){ // Hash algorithms.
                    intent = new Intent(getActivity(), TryHashAlgorithmActivity.class);
                }else{ // Applied algorithms.
                    intent = new Intent(getActivity(), TryAppliedAlgorithmActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    /**
     * Bottom sheet callback, communication between fragments.
     */
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback
            = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
}