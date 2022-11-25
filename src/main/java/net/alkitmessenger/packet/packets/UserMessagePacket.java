package net.alkitmessenger.packet.packets;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.AlkitMessenger;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.message.Message;
import net.alkitmessenger.user.message.PrivateMessages;

import java.io.PrintWriter;

@Value
public class UserMessagePacket extends Packet {

    Long id;
    Message message;

    PrivateMessages privateMessages;

    public UserMessagePacket(Long id, Message message) {

        this.id = id;
        this.message = message;

        privateMessages = AlkitMessenger.getAlkitMessenger().getPrivateMessagesManager().getByID(id);

    }

    public void work() throws PacketWorkException {

        if (privateMessages == null)
            throw new NullPointerException("PrivateMessages not found");
        else {

            User target = privateMessages.getSecondUser(message.getAuthor());

            if (target != null)
                AlkitMessenger.getAlkitMessenger().getServer().getConnectionByUser(target).addPacket(new UserMessagePacket(target.getId(), message));

        }
    }

    public void serialize(@NonNull PrintWriter printWriter) {

        printWriter.println(getID(getClass()));

        writeObject(printWriter, id);
        writeObject(printWriter, message);

        printWriter.flush();

    }
}