package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.Server;
import net.alkitmessenger.user.UserManager;
import net.alkitmessenger.util.CryptorAndDecryptorUtil;
import net.alkitmessenger.util.HashUtil;

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

        CryptorAndDecryptorUtil crypto = new CryptorAndDecryptorUtil();

        byte[] test = new byte[5];
        test[0] = 8;
        byte[] aboba = crypto.byteCryptor(test);
        for (int i = 0; i < aboba.length; i++) {
            System.out.println(aboba[i]);
        }
    }

    public AlkitMessenger() throws IOException {

        server = new Server(9090);
        userManager = new UserManager();

    }
}