package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;
import net.alkitmessenger.user.User;

import java.util.Optional;
import java.util.Queue;

public record LoginPacket(String str) implements Packet {

    public Optional<Queue<Packet>> work() throws PacketWorkException {

        String[] split = str.split(";");

        if (split.length != 2)
            throw new PacketWorkException();

        User user = AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(Long.parseLong(split[0]));

        if (user == null)
            throw new PacketWorkException();

        if (!user.equalsPassword(split[1]))
            throw new PacketWorkException();

        return Optional.empty();

    }

    public String serialize() {

        return str.split(";")[0];

    }
}