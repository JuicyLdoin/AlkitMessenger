package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.packet.PacketSerialize;
import net.alkitmessenger.packet.packets.input.AuthorizePacket;
import net.alkitmessenger.packet.packets.input.UserLoginPacket;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.UserConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Server extends Thread {

    final ServerSocket serverSocket;
    final List<UserConnection> userConnections;

    final PrintStream outMessage;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        userConnections = new ArrayList<>();

        outMessage = System.out;

        start();

    }

    public UserConnection getConnectionByUser(@NonNull User user) {

        return userConnections.stream()
                .filter(userConnection -> userConnection.getUser().equals(user))
                .toList()
                .get(0);

    }

    public void sendMessage(@NonNull String msg) {

        outMessage.println(msg);

    }

    @Override
    public void run() {

        while (true)
            try {

                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("connection initialized");

                while (true) {

                    if (!in.ready())
                        continue;

                    AuthorizePacket authorizePacket = (AuthorizePacket) PacketSerialize.serialize(in);
                    System.out.println("received authorizePacket, " + authorizePacket.getCode());

                    authorizePacket.work();

                    UserLoginPacket loginPacket = (UserLoginPacket) PacketSerialize.serialize(in);
                    loginPacket.work();

                    UserConnection userConnection = new UserConnection(socket, loginPacket.getUser());

                    userConnections.add(userConnection);

                    break;

                }
            } catch (Exception exception) {

                exception.printStackTrace();

            }
    }
}
