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

    String mail;
    String password;

    public UserLoginPacket(@NonNull String mail, @NonNull String password) {

        user = AlkitMessenger.getAlkitMessenger().getUserManager().getByMail(mail);

        this.mail = mail;
        this.password = password;

    }

    public void work() throws PacketWorkException {

        if (user == null)
            throw new RuntimeException("User not found");

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