package net;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import cli.CLI;

public class TCPClient {
    public void connect() {
        try {
            Socket socket = new Socket("localhost", 8081);
            System.out.println("Connected to server");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println(in.readUTF());

            while (true) {
                String command = scanner.nextLine();
                out.writeUTF(command);
                out.flush();

                String response = in.readUTF();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}