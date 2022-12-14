package net.alkitmessenger.packet.packets.output;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.user.User;

import java.io.PrintWriter;

@Value
public class UserDataPacket extends Packet {

    User user;

    @Override
    public void serialize(@NonNull PrintWriter printWriter) {

        printWriter.println(getID(getClass()));

        writeObject(printWriter, user);

        printWriter.flush();

    }
}