package net;

import geschaeftslogik.Manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public void start(String protocol, Integer capacity) throws IOException {
        if (protocol.equalsIgnoreCase("TCP")) {
            Manager manager = new Manager(capacity);
            try (ServerSocket serverSocket = new ServerSocket(8081)) {
                System.out.println("TCP Server started on port 8081");

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New Client connected");
                    new Thread(() -> handleClient(socket, manager)).start();
                }
            }
        } else {
            System.out.println("UDP not supported yet.");
        }
    }

    private void handleClient(Socket socket, Manager manager) {
        try (
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())
        ) {
            out.writeUTF("Enter CLI commands.");
            out.flush();

            while (true) {
                String command = in.readUTF();
                System.out.println(command);
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }

}
