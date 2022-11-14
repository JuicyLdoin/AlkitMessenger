package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Queue;

public record SendFilePacket(String filePath) implements Packet {

    @Override
    public Optional<Queue<Packet>> work() throws PacketWorkException {

        try {

            File file = new File(filePath);

            DataOutputStream dataOutputStream = null;
            FileInputStream fileInputStream = new FileInputStream(file);

            dataOutputStream.writeLong(file.length());

            int bytes;
            byte[] buffer = new byte[64 * 1024];

            while ((bytes = fileInputStream.read(buffer)) != -1) {

                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();

            }

            fileInputStream.close();

        } catch (IOException ignored) {}

        return Optional.empty();

    }

    @Override
    public String serialize() {

        throw new UnsupportedOperationException();

    }
}