package net.alkitmessenger.server.packet;

import java.io.BufferedWriter;
import java.util.Queue;

public abstract class Packet {

    public abstract void work() throws PacketWorkException;

    public Queue<Packet> feedback() { throw new UnsupportedOperationException(); }

    public void serialize(BufferedWriter bufferedWriter) {}

}