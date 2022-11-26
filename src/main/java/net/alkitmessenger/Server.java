package net.alkitmessenger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketSerialize;
import net.alkitmessenger.packet.packets.input.AuthorizePacket;
import net.alkitmessenger.packet.packets.input.UserConnectPacket;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.UserConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
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

    @Override
    public void run() {

        while (true)
            try {

                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("connection initialized");

                Queue<Packet> packets = new LinkedList<>();

                while (true) {

                    if (!in.ready())
                        continue;

                    packets.add(PacketSerialize.serialize(in));

                    if (packets.size() >= 2) {

                        AuthorizePacket authorizePacket = (AuthorizePacket) packets.poll();
                        authorizePacket.work();

                        UserConnectPacket connectPacket = (UserConnectPacket) packets.poll();
                        assert connectPacket != null;
                        connectPacket.work();

                        System.out.println("User " + connectPacket.getUid() + " connected");

                        UserConnection userConnection = new UserConnection(socket, AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(connectPacket.getUid()));

                        userConnections.add(userConnection);

                        break;

                    }
                }
            } catch (Exception exception) {

                exception.printStackTrace();

            }
    }
}
