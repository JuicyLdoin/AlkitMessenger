package net.alkitmessenger.util;

import java.util.Arrays;
import java.util.Collections;

public class CryptorAndDecryptorUtil {
    public byte[] byteCryptor(byte[] buffer){

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) -buffer[i];

            //дальше мы меняем весь массив местами
            Collections.reverse(Arrays.asList(buffer));
        }

        return buffer;
    }
//    public byte[] byteDecryptor
}
