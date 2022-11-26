package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.packets.output.UserDataPacket;

import java.util.LinkedList;
import java.util.Queue;

@Value
public class UserDataReceivePacket extends Packet {

    Long uid;

    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new UserDataPacket(AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(uid)));

        return queue;

    }
}