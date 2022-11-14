package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.*;

public class ReceiveFilePacket {
    public record receiveMessageFile(String filePath) implements Packet {

        @Override
        public void work() throws PacketWorkException{
            try {
                DataInputStream dataInputStream = null;
                int bytes = 0;
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                long size = dataInputStream.readLong();
                byte[] buffer = new byte[64*1024];
                while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
                    fileOutputStream.write(buffer,0,bytes);
                    size -= bytes;
                }
                fileOutputStream.close();
            } catch (IOException ignored) {

            }
        }

        @Override
        public String serialize() {

            throw new UnsupportedOperationException();

        }
    }
}
