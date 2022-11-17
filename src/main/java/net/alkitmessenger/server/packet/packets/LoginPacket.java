package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;
import net.alkitmessenger.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Queue;

public record LoginPacket(@NotNull String login, @NotNull String hashPassword) implements Packet {

    public Optional<Queue<Packet>> work() throws PacketWorkException {
        User user = AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(Long.parseLong(login));

        if (user == null)
            throw new PacketWorkException();

        if (!user.equalsPassword(hashPassword))
            throw new PacketWorkException();

        return Optional.empty();

    }

    public String serialize() {

        return "susses";

    }
}