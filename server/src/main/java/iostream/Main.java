package iostream;

import network.NioServer;
import receiver.CollectionManager;

import java.io.IOException;

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