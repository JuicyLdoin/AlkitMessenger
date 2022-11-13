package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;
import net.alkitmessenger.util.AuthorizeCodeUtil;

public record AuthorizePacket(String code) implements Packet {

    public void work() throws PacketWorkException {

        if (!code.equals(AuthorizeCodeUtil.CODE))
            throw new PacketWorkException();

    }

    public String serialize() {

        throw new UnsupportedOperationException();

    }
}