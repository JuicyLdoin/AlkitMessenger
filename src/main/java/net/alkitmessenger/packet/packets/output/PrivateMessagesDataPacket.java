package net.alkitmessenger.packet.packets.output;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;

import java.io.PrintWriter;

@Value
public class PrivateMessagesDataPacket extends Packet {

    Long id;

    public void serialize(@NonNull PrintWriter printWriter) {

        printWriter.println(getID(getClass()));

        writeObject(printWriter, AlkitMessenger.getAlkitMessenger().getPrivateMessagesManager().getByID(id));

        printWriter.flush();

    }
}