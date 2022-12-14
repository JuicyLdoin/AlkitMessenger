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
    String mail;
    String password;

    User user;

    public UserRegistrationPacket(String name, String mail, String password) {

        this.name = name;
        this.mail = mail;
        this.password = password;

        user = new User(name, mail, password);

        AlkitMessenger.getAlkitMessenger().getUserManager().getUsers().put(user.getId(), user);

    }

    public void work() throws PacketWorkException {

//        Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
//        Matcher matcher = pattern.matcher(this.mail);
//
//        if (matcher.matches()) {
//
//        MailUtil util = new MailUtil("", "");
//
//        util.send(name, "Hello! Your registration on AlkitMessager was successful", mail);
//
//        } else
//            throw new PacketWorkException();

    }

    @Override
    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new UserDataPacket(user));

        return queue;

    }
}
