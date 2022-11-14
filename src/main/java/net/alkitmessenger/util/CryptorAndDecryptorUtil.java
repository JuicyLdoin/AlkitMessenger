package net.alkitmessenger.util;

public class CryptorAndDecryptorUtil {
    public byte[] byteCryptor(byte[] buffer){
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
}
