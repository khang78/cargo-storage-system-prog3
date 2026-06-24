import net.TCPServer;

import java.io.IOException;

public class startServer {
    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer();
        server.start("TCP", 100);
    }
}
