package net.alkitmessenger.packet.packets.input;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.packet.packets.output.SendToWindowPacket;
import net.alkitmessenger.packet.packets.output.UserDataPacket;
import net.alkitmessenger.user.User;

import java.util.LinkedList;
import java.util.Queue;

@Value
public class UserLoginPacket extends Packet {

    User user;

    Long uid;
    String password;

    public UserLoginPacket(@NonNull Long uid, @NonNull String password) {

        user = AlkitMessenger.getAlkitMessenger().getUserManager().getUserByID(uid);

        this.uid = uid;
        this.password = password;

    }

    public void work() throws PacketWorkException {

        if (user == null)
            throw new PacketWorkException();

        if (!user.getPassword().equals(password))
            throw new RuntimeException("Password not equal");

    }

    public Queue<Packet> feedback() {

        Queue<Packet> feedback = new LinkedList<>();

        feedback.add(new UserDataPacket(user));
        feedback.add(new SendToWindowPacket("main"));

        return feedback;

    }
}