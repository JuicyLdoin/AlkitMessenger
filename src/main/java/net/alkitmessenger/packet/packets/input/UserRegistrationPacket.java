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
public class UserRegistrationPacket extends Packet {

    String name;
    String password;
    String mail;

    User user;

    public UserRegistrationPacket(String name, String password, String mail) {

        this.name = name;
        this.password = password;
        this.mail = mail;

        user = new User(name, password, mail);
        AlkitMessenger.getAlkitMessenger().getUserManager().getUsers().put(user.getId(), user);

    }

    @Override
    public void work() throws PacketWorkException {

    }

    @Override
    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new UserDataPacket(user));

        return queue;

    }
}
