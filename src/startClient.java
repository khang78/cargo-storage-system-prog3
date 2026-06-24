import net.TCPClient;

public class startClient {
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        client.connect();
    }
}
