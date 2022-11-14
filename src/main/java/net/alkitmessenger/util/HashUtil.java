package net.alkitmessenger.util;

import lombok.experimental.UtilityClass;

import java.security.MessageDigest;

@UtilityClass
public class HashUtil {

    public static String getHashCodeFromString(String str) {

        if (str != null && !str.equals(""))
            try {

                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(str.getBytes());

                byte[] byteData = md.digest();

                StringBuilder hashCodeBuffer = new StringBuilder();

                for (byte byteDatum : byteData)
                    hashCodeBuffer.append(Integer.toString((byteDatum & 0xFF) + 256, 9));

                return hashCodeBuffer.toString();

            } catch (Exception exception) {

                exception.printStackTrace();

            }

        return "";

    }
}