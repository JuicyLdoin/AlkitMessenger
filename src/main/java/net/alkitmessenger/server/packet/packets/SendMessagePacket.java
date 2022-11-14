package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.*;

public class SendMessagePacket {
    public record sendMessageText(String message) implements Packet {

        @Override
        public void work() throws PacketWorkException {

        }

        @Override
        public String serialize() {

            throw new UnsupportedOperationException();

        }
    }
}
