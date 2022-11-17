package net.alkitmessenger.server.packet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.packet.packets.input.*;
import net.alkitmessenger.server.packet.packets.output.*;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Packets {

    LOGIN_PACKET((byte) -1, LoginPacket.class),

    AUTHORIZE_PACKET((byte) 0, AuthorizePacket.class),

    USER_DATA_PACKET((byte) 1, UserDataPacket.class),
    SEND_TO_WINDOW_PACKET((byte) 2, SendToWindowPacket.class);

    byte id;
    Class<? extends Packet> clazz;

    Packets(byte id, Class<? extends Packet> clazz) {

        this.id = id;
        this.clazz = clazz;

    }

    public static Packets getByID(byte id) {

        for (Packets packets : values())
            if (packets.getId() == id)
                return packets;

        return null;

    }
}