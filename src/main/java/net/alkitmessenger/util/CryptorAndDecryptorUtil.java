package net.alkitmessenger.util;

public class CryptorAndDecryptorUtil {
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
    public byte[] byteDecryptor(byte[] buffer){
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) -buffer[i]; //меняем знак байта
        }
        //меняем рассположение в массиве
        for (int i = 0; i < buffer.length / 2; i++) {
            int temp = buffer[i];
            buffer[i] = buffer[buffer.length - 1 - i];
            buffer[buffer.length - 1 - i] = (byte) temp;
        }
        return buffer;
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

        return text;
    }
}
