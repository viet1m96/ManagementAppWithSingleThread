package iostream;

import network.NioServer;
import receiver.CollectionManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

public class Main {
    private final static int PORT = 4999;
    public static void main(String[] args) {
        try {
            NioServer server = new NioServer();
            Runtime.getRuntime().addShutdownHook(new Thread(server::emergency));
            server.run(PORT, "/home/cun/IdeaProjects/ManagementAppWithSingleThread/common/src/main/resources/Book1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}