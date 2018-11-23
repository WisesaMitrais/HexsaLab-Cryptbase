package com.mitrais.innovation.cryptbase.activity;

import android.content.pm.ActivityInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.mitrais.innovation.cryptbase.R;
import com.mitrais.innovation.cryptbase.utility.crypto.CaesarCipher;
import com.mitrais.innovation.cryptbase.utility.crypto.OneTimePad;
import com.mitrais.innovation.cryptbase.utility.crypto.ScytaleCipher;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandLight;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandMedium;
import com.mitrais.innovation.cryptbase.utility.font.FontQuicksandRegular;

import java.util.Objects;

public class ClassicCryptographyActivity extends AppCompatActivity{

    /*Declare global variables.*/
    private RelativeLayout headerLayout;
    private FontQuicksandMedium headerName, bodyDesc;
    private FontQuicksandLight headerDesc;
    private LinearLayout headerBackground, layoutKeySpinner, layoutKeyText;
    private ImageView headerImage;
    private CardView radioCardEncrypt, radioCardDecrypt, radioBorderEncrypt, radioBorderDecrypt;
    private Button radioButtonEncrypt, radioButtonDecrypt, buttonEncryptDecrypt;
    private EditText inputEncryptDecrypt, inputKeyText;
    private Spinner keySelection;
    private FontQuicksandRegular outputEncryptDecrypt;
    private boolean isEncrypt;
    private String inputText, outputText;
    private int key, activeCardValue;

    /**
     * OnCreate method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);                          //Set potrait screen mode.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_cryptography);
        initalizedComponents();
        Bundle b = getIntent().getExtras();
        activeCardValue = Objects.requireNonNull(b).getInt("activeCard");                      //Get value of active card.
        selectKeyContent(activeCardValue);
        selectHeader(activeCardValue);
        changeStateRadioButton();
        encryptDecryptProcess();
    }

    /**
     * Initialized components.
     */
    private void initalizedComponents(){
        headerLayout = findViewById(R.id.acc_header_layout);
        headerName = findViewById(R.id.acc_header_name);
        headerDesc = findViewById(R.id.acc_header_desc);
        headerImage = findViewById(R.id.acc_header_image);
        headerBackground = findViewById(R.id.acc_header_background);
        layoutKeySpinner = findViewById(R.id.acc_layout_key_spinner);
        layoutKeyText = findViewById(R.id.acc_layout_key_text);
        bodyDesc = findViewById(R.id.acc_body_desc);
        radioCardEncrypt = findViewById(R.id.acc_radio_card_encrypt);
        radioButtonEncrypt = findViewById(R.id.acc_radio_button_encrypt);
        radioBorderEncrypt = findViewById(R.id.acc_radio_border_encrypt);
        radioCardDecrypt = findViewById(R.id.acc_radio_card_decrypt);
        radioButtonDecrypt = findViewById(R.id.acc_radio_button_decrypt);
        radioBorderDecrypt = findViewById(R.id.acc_radio_border_decrypt);
        keySelection = findViewById(R.id.acc_key_selection);
        inputKeyText = findViewById(R.id.acc_key_text);
        buttonEncryptDecrypt = findViewById(R.id.acc_button_encrypt_decrypt);
        inputEncryptDecrypt = findViewById(R.id.acc_input_encrypt_decrypt);
        outputEncryptDecrypt = findViewById(R.id.acc_output_encrypt_decrypt);
        isEncrypt = true;
    }

    /**
     * Selection process key in spinner which related to active card.
     * @param activeCardValue: value of active card.
     */
    public void selectKeyContent(int activeCardValue){
        if(activeCardValue == 1){
            setKeyContentAction(R.array.caesar_key_array);
        }else if(activeCardValue == 2){
            setKeyContentAction(R.array.scytale_key_array);
        }
    }

    /**
     * Set content of key in spinner.
     * @param keyArray: value of array index.
     */
    public void setKeyContentAction(int keyArray){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                keyArray, R.layout.other_spinner_item);
        adapter.setDropDownViewResource(R.layout.other_spinner_item);
        keySelection.setAdapter(adapter);
    }

    /**
     * Selection process header which related to active card.
     * @param activeCardValue: value of active card.
     */
    public void selectHeader(int activeCardValue){
        if(activeCardValue == 1){
            setHeaderDetail(R.string.tran_name_caesar, R.string.head_name_caesar, R.string.head_desc_caesar,
                    R.drawable.img_caesar, R.string.body_desc_caesar);
            layoutKeySpinner.setVisibility(View.VISIBLE);
            layoutKeyText.setVisibility(View.GONE);
        }else if(activeCardValue == 2){
            setHeaderDetail(R.string.tran_name_scytale, R.string.head_name_scytale, R.string.head_desc_scytale,
                    R.drawable.img_scytale, R.string.body_desc_scytale);
            layoutKeySpinner.setVisibility(View.VISIBLE);
            layoutKeyText.setVisibility(View.GONE);
        }else if(activeCardValue == 3){
            setHeaderDetail(R.string.tran_name_onetimepad, R.string.head_name_onetimepad, R.string.head_desc_onetimepad,
                    R.drawable.img_onetimepad, R.string.body_desc_onetimepad);
            layoutKeySpinner.setVisibility(View.GONE);
            layoutKeyText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set detail of header and body description with setting related to active card.
     * @param headerLayout: represent transition name to start activity.
     * @param headerName: title in header activity.
     * @param headerDesc: subtitle in header activity.
     * @param headerImg: image in header activity.
     * @param bodyDesc: short description of encryption method.
     */
    private void setHeaderDetail(int headerLayout, int headerName, int headerDesc, int headerImg,
                           int bodyDesc){
        this.headerLayout.setTransitionName(getString(headerLayout));
        this.headerName.setText(getString(headerName));
        this.headerDesc.setText(getString(headerDesc));
        this.headerImage.setImageResource(headerImg);
        this.headerBackground.setBackgroundResource(R.drawable.custom_gradient_color_1);
        this.bodyDesc.setText(getString(bodyDesc));
    }

    /**
     * Process to change state of radio button.
     */
    public void changeStateRadioButton(){
        /*If encrypt radio button is selected*/
        radioButtonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEncrypt = true;
                radioButtonStateAction(R.color.color4, R.color.color2, R.color.color3,
                        R.color.color10, R.string.plain_text, R.string.encrypt_button);
            }
        });
        /*If decrypt radio button is selected*/
        radioButtonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEncrypt = false;
                radioButtonStateAction(R.color.color3, R.color.color10, R.color.color4,
                        R.color.color2, R.string.cipher_text, R.string.decrypt_button);
            }
        });
    }

    /**
     * Define an action for radio button which selected.
     * @param colorEncryptRadio: color for encryption radio button.
     * @param colorEncryptFont: color for encryption font.
     * @param colorDecryptRadio: color for decryption radio button.
     * @param colorDecryptFont: color for decryption font.
     * @param textHint: hint text in edit text input.
     * @param textButton: text of encrypt/decrypt button process.
     */
    public void radioButtonStateAction(int colorEncryptRadio, int colorEncryptFont,
                                       int colorDecryptRadio, int colorDecryptFont,
                                       int textHint, int textButton){
        radioCardEncrypt.setCardBackgroundColor(getResources().getColor(colorEncryptRadio,
                null));
        radioButtonEncrypt.setTextColor(getResources().getColor(colorEncryptFont, null));
        radioBorderEncrypt.setCardBackgroundColor(getResources().getColor(colorEncryptRadio,
                null));
        radioCardDecrypt.setCardBackgroundColor(getResources().getColor(colorDecryptRadio,
                null));
        radioButtonDecrypt.setTextColor(getResources().getColor(colorDecryptFont, null));
        radioBorderDecrypt.setCardBackgroundColor(getResources().getColor(colorDecryptRadio,
                null));
        inputEncryptDecrypt.setHint(getString(textHint));
        buttonEncryptDecrypt.setText(getString(textButton));
    }

    /**
     * Encrypt/decrypt button action to cipher selection and do input preprocessing.
     */
    public void encryptDecryptProcess(){
        buttonEncryptDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = inputEncryptDecrypt.getText().toString();
                inputText = inputText.toLowerCase(); //Set string input became lower case.
                if(activeCardValue == 1){
                    key = Integer.parseInt(keySelection.getSelectedItem().toString());
                    outputEncryptDecrypt.setText(processCaesarCipher(inputText, key));
                }else if(activeCardValue == 2){
                    if(inputText.length() > 3){
                        key = Integer.parseInt(keySelection.getSelectedItem().toString());
                        outputEncryptDecrypt.setText(processScytaleCipher(inputText, key));
                    }else{
                        setSnackbarWarning("Input string should be more than 3 chars !");
                    }
                }else if(activeCardValue == 3){
                    String tempInput = inputText;
                    String realKey = inputKeyText.getText().toString();
                    realKey = realKey.toLowerCase();
                    tempInput = tempInput.replace(" ", "")
                            .replace(",", "")
                            .replace(".","");

                    if(tempInput.length() == realKey.length()){
                        outputEncryptDecrypt.setText(processOneTimePad(inputText, realKey));
                    }else{
                        setSnackbarWarning("Length between input and key should be same !");
                    }
                }
            }
        });
    }

    /**
     * Process to encrypt/decrypt using caesar cipher.
     * @param inputText: input string.
     * @param key: key value.
     */
    public String processCaesarCipher(String inputText, int key){
        CaesarCipher caesarCipher = new CaesarCipher(inputText, key);
        if(isEncrypt)
            outputText = caesarCipher.encryptCaesarCipher();
        else
            outputText = caesarCipher.decryptCaesarCipher();
        return outputText;
    }

    /**
     * Process to encrypt/decrypt using scytale cipher.
     * @param inputText: input string.
     * @param key: key value.
     */
    public String processScytaleCipher(String inputText, int key){
        ScytaleCipher scytaleCipher = new ScytaleCipher(inputText, key);
        if(isEncrypt)
            outputText = scytaleCipher.encryptScytaleCipher();
        else
            outputText = scytaleCipher.decryptScytaleCipher();
        return outputText;
    }

    /**
     * Process to encrypt/decrypt using one-time pad.
     * @param inputText: input string.
     * @param key: key string.
     */
    public String processOneTimePad(String inputText, String key){
        OneTimePad oneTimePad = new OneTimePad(inputText, key);
        if(isEncrypt)
            outputText = oneTimePad.encryptOneTimePad();
        else
            outputText = oneTimePad.decryptOneTimePad();
        return outputText;
    }

    /**
     * Show warning message.
     * @param message: the value of snackbar warning text.
     */
    public void setSnackbarWarning(String message){
        Snackbar snackbarWarning = Snackbar.make(this.headerLayout, message, Snackbar.LENGTH_LONG);
        snackbarWarning.show();
    }
}
