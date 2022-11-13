package net.alkitmessenger.user;

import lombok.Value;
import net.alkitmessenger.server.packet.Packet;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

@Value
public class UserConnection extends Thread {

    User user;

    Socket socket;

    BufferedReader in;

    Queue<Packet> outPackets;
    BufferedWriter out;

    public UserConnection(Socket socket, User user) throws IOException {

        this.socket = socket;
        this.user = user;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        outPackets = new LinkedList<>();
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void addPacket(Packet packet) {

        outPackets.add(packet);

    }

    @Override
    public void run() {

        while (true) {

            try {

                Packet packet = outPackets.poll();

                if (packet == null)
                    continue;

                String packetData = packet.serialize();

                out.write(packetData);

            } catch (Exception ignored) {}
        }
    }
}