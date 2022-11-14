package net.alkitmessenger.server;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketSerialize;
import net.alkitmessenger.server.packet.packets.AuthorizePacket;
import net.alkitmessenger.server.packet.packets.LoginPacket;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.UserConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Server extends Thread {

    ServerSocket serverSocket;
    List<UserConnection> userConnections;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        userConnections = new ArrayList<>();

        start();

    }

    public UserConnection getConnectionByUser(User user) {

        return userConnections.stream()
                .filter(userConnection -> userConnection.getUser().equals(user))
                .toList()
                .get(0);

    }

    @Override
    public void run() {

        while (true) {

            try {

                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                AuthorizePacket authorizePacket = (AuthorizePacket) PacketSerialize.serialize(in);
                authorizePacket.work();

                LoginPacket loginPacket = (LoginPacket) PacketSerialize.serialize(in);
                Optional<Queue<Packet>> feedback = loginPacket.work();

                UserConnection userConnection = new UserConnection(socket, AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(loginPacket.serialize()));

                feedback.ifPresent(packets -> packets.forEach(userConnection::addPacket));

                userConnections.add(userConnection);

            } catch (Exception ignored) {}
        }
    }
}
