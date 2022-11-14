package net.alkitmessenger.util;

public class CryptorUtil {

//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
//    ПЕРЕНЕСТИ КЛАСС В КЛИЕНТСКУЮ ЧАСТЬ
    public StringBuffer[] byteCryptor(byte[] buffer){
        StringBuffer[] result = new StringBuffer[buffer.length];
        //меняем рассположение в массиве
        for (int i = 0; i < buffer.length / 2; i++) {
            int temp = buffer[i];
            buffer[i] = buffer[buffer.length - 1 - i];
            buffer[buffer.length - 1 - i] = (byte) temp;
        }

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) -buffer[i]; //меняем знак байта

            if (buffer[i] != 0){
                result[i] = new StringBuffer().append(buffer[i]/26).append(remainsToAlfabet(buffer[i]%26));
            }
        }

        return result;
    }
    public char remainsToAlfabet(int i){
        char[] alphabetm = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] alphabetM = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] result;

        if (i < 0){
            result = alphabetM;
            i = -i;
        }
        else
            result = alphabetm;

        return result[i];
    }

    public String textCryptor(String text){
        char[] tempArr = text.toCharArray();

        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = (char) ((char) (tempArr[i] / 26) + remainsToAlfabet(tempArr[i] % 26));
        }
        for (int i = 0; i < tempArr.length / 2; i++) {
            char temp = tempArr[i];
            tempArr[i] = tempArr[tempArr.length - 1 - i];
            tempArr[tempArr.length - 1 - i] = temp;
        }
        return String.valueOf(tempArr);
    }
}
