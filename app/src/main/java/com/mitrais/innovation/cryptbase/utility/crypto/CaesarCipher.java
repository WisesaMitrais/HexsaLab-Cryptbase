package com.mitrais.innovation.cryptbase.utility.crypto;

import com.mitrais.innovation.cryptbase.utility.crypto.base.ClassicProcedure;

public class CaesarCipher {

    /*Declare global variables.*/
    private String string, result;
    private int key, tempInt, asciiValue;
    private char tempChar;
    private ClassicProcedure classicProcedure;

    /**
    * Create constructor to set base parameter.
     * @param string: plaintext or ciphertext.
     * @param key: value of substitution process.
    */
    public CaesarCipher(String string, int key){
        this.string = string;
        this.key = key;
        classicProcedure = new ClassicProcedure();
    }

    /**
    * Encryption process.
     * @return the string ciphertext.
    */
    public String encryptCaesarCipher(){
        result = "";
        for(int idx = 0; idx < string.length(); idx++){
            asciiValue = (int) string.charAt(idx);
            if(asciiValue == 32 || asciiValue == 44 || asciiValue == 46){ //ASCII code for , . (space).
                tempChar = string.charAt(idx);
            }else{
                tempInt = classicProcedure.letterToNumber(string.charAt(idx));
                tempInt = (tempInt + key) % 26;
                tempChar = classicProcedure.numberToLetter(tempInt);
            }
            result += tempChar;
        }
        return result;
    }

    /**
    * Decryption process.
     * @return the string plaintext.
    */
    public String decryptCaesarCipher(){
        result = "";
        for(int idx = 0; idx < string.length(); idx++){
            asciiValue = (int) string.charAt(idx);
            if(asciiValue == 32 || asciiValue == 44 || asciiValue == 46){ //ASCII code for , . (space).
                tempChar = string.charAt(idx);
            }else{
                tempInt = classicProcedure.letterToNumber(string.charAt(idx));
                tempInt = (tempInt - key) % 26;
                if(tempInt < 0) tempInt += 26;
                tempChar = classicProcedure.numberToLetter(tempInt);
            }
            result += tempChar;
        }
        return result;
    }
}
