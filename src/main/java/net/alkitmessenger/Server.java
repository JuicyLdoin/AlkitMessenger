package net.alkitmessenger;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Server {

//    ArrayList<User> clients = new ArrayList<>();
    public void startServer(int host, int port){
        try {
            ServerSocket serverSocket = new ServerSocket(host, port);
            System.out.println("Server on!");
            while (true) {
                Socket socket = serverSocket.accept();
//                User user = new User();
//                clients.add(user);
//                new UserThread(user).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeServer(int host, int port){
        try {
            ServerSocket serverSocket = new ServerSocket(host, port);
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean getStateServer(int host, int port){
        try {
            ServerSocket serverSocket = new ServerSocket(host, port);
            if (!serverSocket.isClosed())
                return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
