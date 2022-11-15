package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.Server;
import net.alkitmessenger.user.UserManager;
import net.alkitmessenger.util.CryptorUtil;

import java.io.IOException;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AlkitMessenger {

    static AlkitMessenger alkitMessenger;

    public static AlkitMessenger getAlkitMessenger() {

        return alkitMessenger;

    }

    Server server;
    UserManager userManager;

    public static void main(String[] args) throws IOException {

        alkitMessenger = new AlkitMessenger();

        CryptorUtil crypto = new CryptorUtil();


        byte[] test = new byte[5];
        test[0] = -127;
        test[1] = -63;
        test[2] = 127;
        test[3] = -1;
        test[4] = 0;
        StringBuffer[] result = crypto.byteCryptor(test);
        for (StringBuffer stringBuffer : result) {
            System.out.println(stringBuffer);
        }

        System.out.println();
        System.out.println(crypto.textCryptor("Lynarion"));
    }

    public AlkitMessenger() throws IOException {

        server = new Server(9090);
        userManager = new UserManager();

    }
}