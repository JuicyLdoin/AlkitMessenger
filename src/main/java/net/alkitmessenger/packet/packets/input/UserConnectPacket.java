package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.UserManager;

@Value
public class UserConnectPacket extends Packet {

    long uid;

    public void work() throws PacketWorkException {

        UserManager userManager = AlkitMessenger.getAlkitMessenger().getUserManager();

        if (!userManager.getUsers().containsKey(uid))
            userManager.getUsers().put(uid, new User(uid));

    }
}