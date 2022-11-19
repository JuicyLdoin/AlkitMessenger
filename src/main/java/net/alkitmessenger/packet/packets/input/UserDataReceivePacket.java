package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.packet.packets.output.UserDataPacket;
import net.alkitmessenger.user.User;

import java.util.LinkedList;
import java.util.Queue;

@Value
public class UserDataReceivePacket extends Packet {

    User user;
    Long uid;

    public UserDataReceivePacket(long uid) {

        user = AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(uid);
        this.uid = uid;

    }

    public void work() throws PacketWorkException {

        if (user == null)
            throw new PacketWorkException();

    }

    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new UserDataPacket(user));

        return queue;

    }
}