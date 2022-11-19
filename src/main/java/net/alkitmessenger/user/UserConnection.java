package net.alkitmessenger.user;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketSerialize;
import net.alkitmessenger.packet.packets.output.ExceptionPacket;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@Value
public class UserConnection extends Thread {

    User user;

    Socket socket;

    BufferedReader in;

    Queue<Packet> outPackets;
    PrintWriter out;

    public UserConnection(@NonNull Socket socket, @NonNull User user) throws IOException {

        this.socket = socket;
        this.user = user;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        outPackets = new LinkedList<>();
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    // добавление пакета в очередь на отправку пользователю
    public void addPacket(@NonNull Packet packet) {

        outPackets.add(packet);

    }

    @Override
    public void run() {

        while (true)
            try {

                // получение пакетов от пользователя

                while (in.ready())
                    PacketSerialize.serialize(in).work();

                // отправка пакетов пользователю из очереди

                while (!outPackets.isEmpty()) {

                    Packet packet = outPackets.poll();

                    if (packet == null)
                        continue;

                    packet.serialize(out);

                }
            } catch (Exception exception) {

                addPacket(new ExceptionPacket(exception.getMessage()));
                throw new RuntimeException("Error on user " + user.getId() + ". Message: " + exception.getMessage());

            }
    }
}