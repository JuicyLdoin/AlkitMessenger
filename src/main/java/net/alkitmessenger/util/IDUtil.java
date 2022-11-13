package net.alkitmessenger.util;

import com.mysql.cj.exceptions.NumberOutOfRange;
import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@UtilityClass
public class IDUtil {

    public static String generateRandomID(int length) {

        if (length <= 0)
            throw new NumberOutOfRange("Length cannot be less than 1");

        String uuid = UUID.randomUUID().toString().replace("-", "");

        if (uuid.length() < length)
            throw new ArrayIndexOutOfBoundsException("Length of ID cannot be longer than " + uuid.length());

        String[] rawID = new String[length];

        IntStream.range(0, length)
                .forEach(i -> rawID[i] = uuid.split("")[ThreadLocalRandom.current().nextInt(uuid.length())]);

        return String.join("", rawID);

    }
}