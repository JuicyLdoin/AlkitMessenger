package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

public record LoginPacket(long id) implements Packet {

    public void work() throws PacketWorkException {

        if (AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(id) == null)
            throw new PacketWorkException();

    }

    public String serialize() {

        throw new UnsupportedOperationException();

    }
}