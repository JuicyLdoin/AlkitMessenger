package net.alkitmessenger.server.packet;

public interface Packet {

    void work() throws PacketWorkException;

    String serialize();

}