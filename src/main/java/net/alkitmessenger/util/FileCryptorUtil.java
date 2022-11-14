package net.alkitmessenger.util;

public class FileCryptorUtil {
    public byte[] byteCryptor(byte[] buffer){
        byte[] result = buffer;

        //тут можно менять знак байта, менять их расположение, перевести в другую систему счисления, чтобы файл не могли перехватить
        //в общем метод я буду дорабатывать

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (result[i]/2);
            int startIndex = 0;
            int endIndex = result.length;
            int shift = 0;

            int endNumber = result[result.length - shift];

            result[endIndex] = result[startIndex];
            result[startIndex] = result[endNumber];

            startIndex++;
            endIndex--;
            shift++;

        }

        return result;
    }
}
