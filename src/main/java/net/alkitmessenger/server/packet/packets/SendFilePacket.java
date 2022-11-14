package net.alkitmessenger.server.packet.packets;

import net.alkitmessenger.server.packet.Packet;
import net.alkitmessenger.server.packet.PacketWorkException;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SendFilePacket {
    public record sendMessageFile(String filePath) implements Packet {

        @Override
        public void work() throws PacketWorkException{
            try {
                DataOutputStream dataOutputStream = null;
                int bytes = 0;
                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);

                dataOutputStream.writeLong(file.length());
                byte[] buffer = new byte[64*1024];
                while ((bytes=fileInputStream.read(buffer))!=-1){
                    dataOutputStream.write(buffer,0,bytes);
                    dataOutputStream.flush();
                }
                fileInputStream.close();
            } catch (IOException ignored) {

            }
        }

        @Override
        public String serialize() {

            throw new UnsupportedOperationException();

        }
    }
}
