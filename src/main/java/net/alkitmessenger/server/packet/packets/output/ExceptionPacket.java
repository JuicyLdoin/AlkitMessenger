package net.alkitmessenger.server.packet.packets.output;

import lombok.Value;
import net.alkitmessenger.server.packet.Packet;

import java.io.PrintWriter;

@Value
public class ExceptionPacket extends Packet {

    String exception;

    public void serialize(PrintWriter printWriter) {

        writeObject(printWriter, exception);

    }
}