package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.util.Optional;
import java.util.Queue;

public record SendMessagePacket(String cryptText, long interlocutorID) implements Packet {

    @Override
    public Optional<Queue<Packet>> work() throws PacketWorkException {

        return Optional.empty();

    }

    @Override
    public String serialize() {

        AlkitMessenger.getAlkitMessenger().getServer().sendMessage(cryptText);
        return cryptText;

    }
}