package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.user.User;

@Value
public class UserDisconnectPacket extends Packet {

    long uid;

    public void work() throws PacketWorkException {

        User user = AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(uid);

        if (user == null)
            throw new PacketWorkException();

        user.setLogined(true);

    }
}