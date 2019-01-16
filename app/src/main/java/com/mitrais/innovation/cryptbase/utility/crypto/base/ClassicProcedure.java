package com.mitrais.innovation.cryptbase.utility.crypto.base;

public class ClassicProcedure {

    /*Declare and initialized relation of letter and number (index of array).*/
    private char[] letterList = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                                 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
    * Convertion a letter to a number based on letter list setting.
     * @param letter: a character of plaintext or ciphertext.
     * @return an index of array, represent number value of character.
    */
    public int letterToNumber(char letter){
        int resultInt = 0;
        while(letter != letterList[resultInt]){
            resultInt++;
        }
        return resultInt;
    }

    /**
    * Convertion a number to a letter based on letter list setting.
     * @param number: a value of array index.
     * @return a character of plaintext or ciphertext.
    */
    public char numberToLetter(int number){
        return letterList[number];
    }

    /**
     * Transpose value of array with a key parameter.
     * @param string: a string of input.
     * @param key: a value to became parameter
     * @return a transposed string.
     */
    public String transposeArray(String string,  int key){
        if(string.length() % key != 0) key++; //Increment key if  !(key | string.length)
        StringBuilder resultString = new StringBuilder();
        for(int idx = 0; idx < key; idx++){
            int counter = idx;
            while(counter < string.length()){
                resultString.append(string.charAt(counter));
                counter += key;
            }
        }
        return resultString.toString();
    }

    /*for one time pad, must handle random process to generate key*/
}
