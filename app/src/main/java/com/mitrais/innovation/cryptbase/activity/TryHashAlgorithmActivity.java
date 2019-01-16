package com.mitrais.innovation.cryptbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandBold;

public class TryHashAlgorithmActivity extends AppCompatActivity {

    /* Global variables. */
    private static final int[] HEAD_NAME = {R.string.head_name_md, R.string.head_name_sha,
            R.string.head_name_hmac};
    private FontQuicksandBold headName;
    private int cardValue;

    /**
     * onCreate method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_hash_algorithm);
        initializeComponents();
    }

    /**
     * Initialized all components in this activity.
     */
    public void initializeComponents(){
        headName = findViewById(R.id.atha_head_name);
        cardValue = getIntent().getIntExtra("cardValue", 0);
        cardValue -= 9;
        headName.setText(HEAD_NAME[cardValue - 1]);
    }
}
