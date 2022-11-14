package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;
import net.alkitmessenger.util.AuthorizeCodeUtil;

import java.util.Optional;
import java.util.Queue;

public record AuthorizePacket(String code) implements Packet {

    public Optional<Queue<Packet>> work() throws PacketWorkException {

        if (!code.equals(AuthorizeCodeUtil.CODE))
            throw new PacketWorkException();

        return Optional.empty();

    }

    public String serialize() {

        throw new UnsupportedOperationException();

    }
}