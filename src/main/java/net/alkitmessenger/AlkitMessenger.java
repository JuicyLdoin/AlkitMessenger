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
        test[0] = 56;
        test[1] = -35;
        test[2] = 12;
        test[3] = -65;
        test[4] = 24;
        StringBuffer[] result = crypto.byteCryptor(test);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public AlkitMessenger() throws IOException {

        server = new Server(9090);
        userManager = new UserManager();

    }
}