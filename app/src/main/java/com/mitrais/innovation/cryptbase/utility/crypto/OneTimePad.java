package com.mitrais.innovation.cryptbase.utility.crypto;

import com.mitrais.innovation.cryptbase.utility.crypto.base.ClassicProcedure;

import java.util.List;

public class OneTimePad {

    /*Declare global variables.*/
    private String string, key, result;
    private int asciiValueInput, idxKey, tempIntInput, tempIntKey;
    private char tempChar;
    private ClassicProcedure classicProcedure;

    /**
     * Create constructor to set base parameter.
     * @param string: plaintext or ciphertext.
     */
    public OneTimePad(String string, String key){
        this.string = string;
        this.key = key;
        classicProcedure = new ClassicProcedure();
    }

    /**
     * Encryption process.
     * @return the string ciphertext.
     */
    public String encryptOneTimePad(){
        result = "";
        idxKey = 0;
        for(int idx = 0; idx < string.length(); idx++){
            asciiValueInput = (int) string.charAt(idx);
            if(asciiValueInput == 32 || asciiValueInput == 44 || asciiValueInput == 46){ //ASCII code for , . (space).
                tempChar = string.charAt(idx);
            }else{
                tempIntInput = classicProcedure.letterToNumber(string.charAt(idx));
                tempIntKey = classicProcedure.letterToNumber(key.charAt(idxKey));
                tempIntInput = (tempIntInput + tempIntKey) % 26;
                tempChar = classicProcedure.numberToLetter(tempIntInput);
                idxKey++;
            }
            result += tempChar;
        }
        return result;
    }

    /**
     * Decryption process.
     * @return the string plaintext.
     */
    public String decryptOneTimePad(){
        result = "";
        for(int idx = 0; idx < string.length(); idx++){
            asciiValueInput = (int) string.charAt(idx);
            if(asciiValueInput == 32 || asciiValueInput == 44 || asciiValueInput == 46){ //ASCII code for , . (space).
                tempChar = string.charAt(idx);
            }else{
                tempIntInput = classicProcedure.letterToNumber(string.charAt(idx));
                tempIntKey = classicProcedure.letterToNumber(key.charAt(idxKey));
                tempIntInput = (tempIntInput - tempIntKey) % 26;
                if(tempIntInput < 0)
                    tempIntInput += 26;
                tempChar = classicProcedure.numberToLetter(tempIntInput);
                idxKey++;
            }
            result += tempChar;
        }
        return result;
    }
}
