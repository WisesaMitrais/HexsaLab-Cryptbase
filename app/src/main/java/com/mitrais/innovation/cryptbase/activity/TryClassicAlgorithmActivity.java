package com.mitrais.innovation.cryptbase.activity;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.utility.crypto.CaesarCipher;
import com.mitrais.innovation.cryptbase.utility.crypto.OneTimePad;
import com.mitrais.innovation.cryptbase.utility.crypto.ScytaleCipher;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandBold;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandMedium;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandRegular;

public class TryClassicAlgorithmActivity extends AppCompatActivity {

    /* Global variables. */
    private static final int[] HEAD_NAME = {R.string.head_name_caesar, R.string.head_name_scytale,
            R.string.head_name_onetimepad};
    private FontQuicksandBold headName;
    private int cardValue;
    private EditText plain, cipher;
    private EditText keyEncrypt, keyDecrypt;
    private FontQuicksandRegular encryptNote, decryptNote;
    private Button encrypt, decrypt;
    private FontQuicksandMedium rsltEncrypt, rsltDecrypt;

    /**
     * onCreate method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_classic_algorithm);
        initializeComponents();
        encryptionProcess();
        decryptionProcess();
    }

    /**
     * Initialized all components in this activity.
     */
    public void initializeComponents(){
        headName = findViewById(R.id.atca_head_name);
        plain = findViewById(R.id.atca_plain);
        cipher = findViewById(R.id.atca_cipher);
        keyEncrypt = findViewById(R.id.atca_key_encrypt);
        keyDecrypt = findViewById(R.id.atca_key_decrypt);
        encryptNote = findViewById(R.id.atca_encrypt_note);
        decryptNote = findViewById(R.id.atca_decrypt_note);
        encrypt = findViewById(R.id.atca_button_encrypt);
        decrypt = findViewById(R.id.atca_button_decrypt);
        rsltEncrypt = findViewById(R.id.atca_encrypt_result);
        rsltDecrypt = findViewById(R.id.atca_decrypt_result);
        setDefaultValueComponents();
    }

    /**
     * Set default value for components dynamically based on card value.
     */
    public void setDefaultValueComponents(){
        cardValue = getIntent().getIntExtra("cardValue", 0);
        headName.setText(HEAD_NAME[cardValue - 1]);
        if(cardValue == 1){ // Caesar cipher.
            encryptNote.setText("Note: The key values are between 1 - 25.");
            decryptNote.setText("Note: The key values are between 1 - 25.");
        }else if(cardValue == 2){ // Scytale cipher.
            encryptNote.setText("Note: The key values are 2 / 3 / 5.");
            decryptNote.setText("Note: The key values are 2 / 3 / 5.");
        }else if(cardValue == 3){ // One-time pad cipher.
            encryptNote.setText("Note: The key length should be same with plain.");
            decryptNote.setText("Note: The key length should be same with cipher.");
        }
    }

    /**
     * Set action for encryption process.
     */
    public void encryptionProcess(){
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plaintext = plain.getText().toString();
                plaintext = plaintext.toLowerCase();
                int key = Integer.parseInt(keyEncrypt.getText().toString());
                if(cardValue == 1){
                    if(key >= 1 && key <= 25){
                        CaesarCipher caesarCipher = new CaesarCipher(plaintext, key);
                        rsltEncrypt.setText(caesarCipher.encryptCaesarCipher());
                    }else{
                        callSnackbar(R.string.key_wrong_value);
                    }
                }else if(cardValue == 2){
                    if(key == 2 || key == 3 || key == 5){
                        ScytaleCipher scytaleCipher = new ScytaleCipher(plaintext, key);
                        rsltEncrypt.setText(scytaleCipher.encryptScytaleCipher());
                    }else{
                        callSnackbar(R.string.key_wrong_value);
                    }
                }else if(cardValue == 3){
//                    if(keyEncrypt.getText().toString().length() == plaintext.length()){
//                        OneTimePad oneTimePad = new OneTimePad(plaintext,
//                                keyEncrypt.getText().toString());
//                        rsltEncrypt.setText(oneTimePad.encryptOneTimePad());
//                    }else{
//                        callSnackbar(R.string.key_wrong_value);
//                    }
                }
            }
        });
    }

    /**
     * Set action for decryption process.
     */
    public void decryptionProcess(){
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ciphertext = cipher.getText().toString();
                ciphertext = ciphertext.toLowerCase();
                int key = Integer.parseInt(keyDecrypt.getText().toString());
                if(cardValue == 1){
                    if(key >= 1 && key <= 25){
                        CaesarCipher caesarCipher = new CaesarCipher(ciphertext, key);
                        rsltDecrypt.setText(caesarCipher.decryptCaesarCipher());
                    }else{
                        callSnackbar(R.string.key_wrong_value);
                    }
                }else if(cardValue == 2){
                    if(key == 2 || key == 3 || key == 5){
                        ScytaleCipher scytaleCipher = new ScytaleCipher(ciphertext, key);
                        rsltDecrypt.setText(scytaleCipher.decryptScytaleCipher());
                    }else{
                        callSnackbar(R.string.key_wrong_value);
                    }
                }else if(cardValue == 3){
//                    if(keyDecrypt.getText().toString().length() == ciphertext.length()){
//                        OneTimePad oneTimePad = new OneTimePad(ciphertext,
//                                keyDecrypt.getText().toString());
//                        rsltEncrypt.setText(oneTimePad.decryptOneTimePad());
//                    }else{
//                        callSnackbar(R.string.key_wrong_value);
//                    }
                }
            }
        });
    }

    /**
     * Show warning or error message with snackbar.
     */
    public void callSnackbar(int messageID){
        Snackbar warning = Snackbar.make(findViewById(R.id.layout), messageID,
                BaseTransientBottomBar.LENGTH_SHORT);
        warning.show();
    }
}
