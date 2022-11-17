package net.alkitmessenger.server.packet;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PacketSerialize {

    public static Packet serialize(BufferedReader bufferedReader) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (!bufferedReader.ready())
            throw new IllegalAccessException();

        byte packetID = (byte) bufferedReader.read();
        Packets packets = Packets.getByID(packetID);

        if (packets == null)
            throw new NullPointerException();

        Class<? extends Packet> clazz = packets.getClazz();

        List<String> args = new ArrayList<>();

        while (bufferedReader.ready())
            args.add(bufferedReader.readLine());

        Object[] objects = new Object[args.size()];

        for (int i = 0; i < args.size(); i++)
            objects[i] = args.get(i);

        return (Packet) clazz.getConstructors()[0].newInstance(objects);

    }
}