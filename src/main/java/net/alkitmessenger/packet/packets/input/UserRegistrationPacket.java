package net.alkitmessenger.packet.packets.input;

import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.packet.packets.output.UserDataPacket;
import net.alkitmessenger.user.User;
import net.alkitmessenger.util.MailUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");

        Matcher m = pattern.matcher(this.mail);

        if (m.matches()){
//        MailUtil util = new MailUtil("", "");
//
//        util.send(name, "Hello! Your registration on AlkitMessager was successful", mail);
//

        } else new PacketWorkException();
    }
    @Override
    public Queue<Packet> feedback() {

        Queue<Packet> queue = new LinkedList<>();

        queue.add(new UserDataPacket(user));

        return queue;

    }
}
