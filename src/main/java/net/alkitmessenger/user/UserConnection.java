package net.alkitmessenger.user;

import lombok.Value;

import java.util.Queue;

@Value
public class UserConnection extends Thread {

    User user;

    Queue<?> packetsToSend;
    Queue<?> packetsToReceive;

    public void addPacket() {

        

    }

    @Override
    public void run() {



    }
}