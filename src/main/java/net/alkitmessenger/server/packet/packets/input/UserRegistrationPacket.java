package net.alkitmessenger.server.packet.packets.input;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
import net.alkitmessenger.packet.Packet;
import net.alkitmessenger.packet.PacketWorkException;
import net.alkitmessenger.user.User;

import java.io.PrintWriter;

@Value
@Getter
@Setter
public class UserRegistrationPacket extends Packet {
    String password;
    String mail;
    String name;
    public UserRegistrationPacket(String password, String mail, String name) {
        this.password = password;
        this.mail = mail;
        this.name = name;
    }

    @Override
    public void work() throws PacketWorkException {
       User user = new User(name, mail);

    }

    @Override
    public void serialize(@NonNull PrintWriter printWriter) {
        super.serialize(printWriter);
    }
}
