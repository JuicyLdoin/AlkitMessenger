package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;

@Value
public class UserConnectPacket extends Packet {

    Long uid;

    public void work() throws PacketWorkException {

        AlkitMessenger.getAlkitMessenger().getUserManager().createUser(uid);

    }
}