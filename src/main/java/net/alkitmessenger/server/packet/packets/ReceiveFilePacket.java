package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.*;
import java.util.Optional;
import java.util.Queue;

public record ReceiveFilePacket(String filePath) implements Packet {

    @Override
    public Optional<Queue<Packet>> work() throws PacketWorkException {

        try {

            DataInputStream dataInputStream = null;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            int bytes;

            long size = dataInputStream.readLong();
            byte[] buffer = new byte[64 * 1024];

            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {

                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;

            }

            fileOutputStream.close();

        } catch (IOException ignored) {}

        return Optional.empty();

    }

    @Override
    public String serialize() {

        throw new UnsupportedOperationException();

    }
}
