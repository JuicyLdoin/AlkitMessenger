package net.alkitmessenger.server;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.packet.PacketSerialize;
import net.alkitmessenger.server.packet.packets.input.AuthorizePacket;
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

        while (true) {

            try {

                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                AuthorizePacket authorizePacket = (AuthorizePacket) PacketSerialize.serialize(in);
                authorizePacket.work();

//                LoginPacket loginPacket = (LoginPacket) PacketSerialize.serialize(in);
//                Optional<Queue<Packet>> feedback = loginPacket.work();
//
//                UserConnection userConnection = new UserConnection(socket, AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(loginPacket.serialize()));
//
//                feedback.ifPresent(packets -> packets.forEach(userConnection::addPacket));
//
//                userConnections.add(userConnection);

            } catch (Exception ignored) {}
        }
    }
}
