package net.alkitmessenger.packet;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.util.Queue;

public abstract class Packet {

    public void work() throws PacketWorkException {
    }

    public Queue<Packet> feedback() {
        throw new UnsupportedOperationException();
    }

    public void serialize(@NotNull PrintWriter printWriter) {
    }

    protected <T> void writeObject(@NotNull PrintWriter printWriter, @NotNull T parent) {

        new Gson().toJson(new PacketData<>(parent), printWriter);
        printWriter.println();

    }

    protected int getID(@NotNull Class<? extends Packet> clazz) {

        for (Packets packets : Packets.values())
            if (packets.getClazz().equals(clazz))
                return packets.getId();

        return -1;

    }
}