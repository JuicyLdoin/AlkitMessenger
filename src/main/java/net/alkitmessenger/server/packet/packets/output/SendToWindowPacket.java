package net.alkitmessenger.server.packet.packets.output;

import lombok.Value;
import net.alkitmessenger.server.packet.Packet;

import java.io.PrintWriter;

@Value
public class SendToWindowPacket extends Packet {

    String windowPath;

    public void serialize(PrintWriter printWriter) {

        writeObject(printWriter, windowPath);

    }
}