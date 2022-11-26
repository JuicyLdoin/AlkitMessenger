package net.alkitmessenger.user;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketFeedback;
import net.alkitmessenger.packet.PacketSerialize;
import net.alkitmessenger.packet.packets.ExceptionPacket;
import net.alkitmessenger.packet.packets.input.UserDisconnectPacket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Value
public class UserConnection extends Thread {

    User user;

    Socket socket;

    BufferedReader in;

    Queue<Packet> outPackets;
    PrintWriter out;

    List<PacketFeedback> packetFeedback;

    public UserConnection(@NonNull Socket socket, @NonNull User user) throws IOException {

        this.socket = socket;
        this.user = user;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        outPackets = new LinkedList<>();
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        packetFeedback = new ArrayList<>();

        start();

    }

    // добавление пакета в очередь на отправку пользователю
    public void addPacket(@NonNull Packet packet) {

        outPackets.add(packet);

    }

    public void addPacketFeedBack(PacketFeedback feedback) {

        packetFeedback.add(feedback);

    }

    @Override
    public void run() {

        while (true)
            try {

                // получение пакетов от пользователя

                while (in.ready()) {

                    Packet inputPacket = PacketSerialize.serialize(in);

                    List<PacketFeedback> toRemove = new ArrayList<>();

                    packetFeedback.forEach(feedback -> {

                        feedback.setReceivedPacket(inputPacket);
                        feedback.resume(inputPacket instanceof ExceptionPacket ? PacketFeedback.Reason.EXCEPTION : PacketFeedback.Reason.PACKET);

                        if (feedback.isRead())
                            toRemove.add(feedback);

                    });

                    toRemove.forEach(packetFeedback::remove);

                    inputPacket.work();

                    try {

                        outPackets.addAll(inputPacket.feedback());

                    } catch (Exception ignored) {}

                    if (inputPacket instanceof UserDisconnectPacket) {

                        socket.close();
                        AlkitMessenger.getAlkitMessenger().getServer().getUserConnections().remove(this);

                        System.out.println("User " + user.getId() + " disconnected");

                        stop();

                        return;

                    }
                }

                // отправка пакетов пользователю из очереди

                while (!outPackets.isEmpty()) {

                    Packet packet = outPackets.poll();

                    if (packet == null)
                        continue;

                    packet.serialize(out);

                }
            } catch (Exception exception) {

                addPacket(new ExceptionPacket(exception.getMessage()));
                System.out.println("Error on user " + user.getId() + ". Message: " + exception.getMessage());

            }
    }
}