package net.alkitmessenger.server.packet;

import java.util.Optional;
import java.util.Queue;

public interface Packet {

    Optional<Queue<Packet>> work() throws PacketWorkException;

    String serialize();

}