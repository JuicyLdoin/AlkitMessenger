package net.alkitmessenger.user;

import lombok.Value;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketSerialize;

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

    // добавление пакета в очередь на отправку пользователю
    public void addPacket(Packet packet) {

        outPackets.add(packet);

    }

    @Override
    public void run() {

        while (true) {

            try {

                // получение пакетов от пользователя

                while (in.ready())
                    PacketSerialize.serialize(in).work();

                // отправка пакетов пользователю из очереди

                while (!outPackets.isEmpty()) {

                    Packet packet = outPackets.poll();

                    if (packet == null)
                        continue;

                    Queue<Packet> packetData = packet.feedback();

                    while (!packetData.isEmpty())
                        packetData.poll().serialize(out);

                }
            } catch (Exception ignored) {}
        }
    }
}