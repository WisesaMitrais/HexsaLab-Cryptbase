package com.mitrais.innovation.cryptbase.utility.crypto;

import com.mitrais.innovation.cryptbase.utility.crypto.base.ClassicProcedure;

public class ScytaleCipher {

    /*Declare global variables.*/
    private String string, result;
    private int key;
    private ClassicProcedure classicProcedure;

    /**
     * Create constructor to set base parameter.
     * @param string: plaintext or ciphertext.
     * @param key: value of transposition process.
     */
    public ScytaleCipher(String string, int key){
        this.string = string;
        this.key = key;
        classicProcedure = new ClassicProcedure();
    }

    /**
     * Encryption process.
     * @return the string ciphertext.
     */
    public String encryptScytaleCipher(){
        int addChar = key - (string.length() % key);
        for(int i = 1; i <= addChar; i++){
            string += "x";
        }
        result = classicProcedure.transposeArray(string, key);
        return result;
    }

    /**
     * Decryption process.
     * @return the string plaintext.
     */
    public String decryptScytaleCipher(){
        key = string.length() / key;
        result = classicProcedure.transposeArray(string, key);
        return result;
    }
}
