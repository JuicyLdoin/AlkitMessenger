package net.alkitmessenger.server.packet;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Queue;

public abstract class Packet {

    public void work() throws PacketWorkException {}

    public Queue<Packet> feedback() { throw new UnsupportedOperationException(); }

    public void serialize(PrintWriter printWriter) {}

    protected <T> void writeField(PrintWriter printWriter, Field field, T parent) throws IllegalAccessException {

        new Gson().toJson(new PacketData<>(field.get(parent)), printWriter);
        printWriter.println();

    }

    protected <T> void writeObject(PrintWriter printWriter, T parent) {

        new Gson().toJson(new PacketData<>(parent), printWriter);
        printWriter.println();

    }

    protected int getID(Class<? extends Packet> clazz) {

        for (Packets packets : Packets.values())
            if (packets.getClazz().equals(clazz))
                return packets.getId();

        return -1;

    }
}