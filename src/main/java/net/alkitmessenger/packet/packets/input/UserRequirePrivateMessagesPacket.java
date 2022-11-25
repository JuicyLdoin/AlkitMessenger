package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.packet.packets.output.PrivateMessagesDataPacket;

import java.util.LinkedList;
import java.util.Queue;

@Value
public class UserRequirePrivateMessagesPacket extends Packet {

    Long id;

    public void work() throws PacketWorkException {

        if (AlkitMessenger.getAlkitMessenger().getPrivateMessagesManager().getByID(id) == null)
            throw new NullPointerException("PrivateMessages not found");

    }

    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new PrivateMessagesDataPacket(id));

        return queue;

    }
}