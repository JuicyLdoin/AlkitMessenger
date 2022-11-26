package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.user.UserManager;
import net.alkitmessenger.user.message.PrivateMessagesManager;

import java.io.IOException;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AlkitMessenger {

    private static AlkitMessenger alkitMessenger;
    Server server;
    UserManager userManager;
    PrivateMessagesManager privateMessagesManager;
    public AlkitMessenger() throws IOException {

        server = new Server(9090);

        userManager = new UserManager();
        privateMessagesManager = new PrivateMessagesManager();

    }

    public static AlkitMessenger getAlkitMessenger() {

        return alkitMessenger;

    }

    public static void main(String[] args) throws IOException {

        alkitMessenger = new AlkitMessenger();

    }
}