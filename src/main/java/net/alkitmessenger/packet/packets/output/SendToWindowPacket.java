package net.alkitmessenger.packet.packets.output;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.packet.Packet;

import java.io.PrintWriter;

@Value
public class SendToWindowPacket extends Packet {

    String windowPath;

    public SendToWindowPacket(@NonNull String windowPath) {

        this.windowPath = "/scenes/" + windowPath + "Scene.fxml";

    }

    public void serialize(@NonNull PrintWriter printWriter) {

        printWriter.println(getID(getClass()));

        writeObject(printWriter, windowPath);

        printWriter.flush();

    }
}