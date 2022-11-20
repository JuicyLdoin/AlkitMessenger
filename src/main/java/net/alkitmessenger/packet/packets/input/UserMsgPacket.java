package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;

@Value
public class UserMsgPacket extends Packet {
    @Override
    public void work() throws PacketWorkException {

    }
}
