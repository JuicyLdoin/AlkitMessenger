package net.alkitmessenger.server.packet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.server.packet.packets.AuthorizePacket;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Packets {

    AUTHORIZE_PACKET((short) 0, AuthorizePacket.class);

    short id;
    Class<? extends Packet> clazz;

    Packets(short id, Class<? extends Packet> clazz) {

        this.id = id;
        this.clazz = clazz;

    }

    public static Packets getByID(short id) {

        for (Packets packets : values())
            if (packets.getId() == id)
                return packets;

        return null;

    }
}