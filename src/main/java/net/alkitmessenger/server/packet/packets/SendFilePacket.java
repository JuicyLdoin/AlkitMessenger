package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.util.Optional;
import java.util.Queue;

public record SendFilePacket(StringBuffer[] cryptBytes, long interlocutorID) implements Packet {

    @Override
    public Optional<Queue<Packet>> work() throws PacketWorkException {

        return Optional.empty();
    }

    @Override
    public String serialize() {


        return null;
    }
}