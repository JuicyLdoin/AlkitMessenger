package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.Server;
import net.alkitmessenger.user.UserManager;
import net.alkitmessenger.user.message.PrivateMessagesManager;

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
    PrivateMessagesManager privateMessagesManager;

    public static void main(String[] args) throws IOException {

        alkitMessenger = new AlkitMessenger();

    }

    public AlkitMessenger() throws IOException {



        server = new Server(9090);

        userManager = new UserManager();
        privateMessagesManager = new PrivateMessagesManager();

    }
}