package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.UserManager;

@Value
public class UserUpdatePacket extends Packet {

    User user;

    public void work() throws PacketWorkException {

        UserManager userManager = AlkitMessenger.getAlkitMessenger().getUserManager();

        userManager.getUsers().put(user.getId(), user);
        userManager.saveUser(user);

    }
}