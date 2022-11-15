package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.Server;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.IOException;
import java.util.Optional;
import java.util.Queue;

public record SendMessagePacket(String cryptText, long interlocutorID) implements Packet {

    @Override
    public Optional<Queue<Packet>> work() throws PacketWorkException {

        return Optional.empty();

    }

    @Override
    public String serialize() {

        try {
            Server server = new Server(9090);
            server.sendMsg(cryptText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cryptText;
    }
}