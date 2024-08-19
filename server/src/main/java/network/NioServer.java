package network;

import goods.Request;
import goods.Response;
import receiver.CollectionManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class NioServer {
    private ServerSocketChannel ssChannel;
    private Selector selector;
    private ByteBuffer data;
    private ByteArrayInputStream bais;
    private ByteArrayOutputStream baos;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Set<SocketChannel> clients;
    private Invoker invoker;
    private CollectionManager collector;

    public NioServer() throws IOException {
        ssChannel = null;
        selector = null;
        clients = new HashSet<>();
    }

    private void init(final int PORT, String fileName) throws IOException {
        ssChannel = ServerSocketChannel.open();
        selector = Selector.open();
        ssChannel.configureBlocking(false);
        ssChannel.socket().bind(new InetSocketAddress(PORT));
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        collector = new CollectionManager(fileName);
        invoker = new Invoker();
    }

    public void run(final int PORT, String fileName) throws IOException {
        try {
            this.init(PORT, fileName);
            collector.loadData();
            while(true) {
                selector.select(10090);
                if(selector.selectedKeys().isEmpty()) {
                    System.exit(0);
                }
                for(SelectionKey key : selector.selectedKeys()) {
                    if(key.isAcceptable()) {
                        if(key.channel() instanceof ServerSocketChannel server) {
                            SocketChannel sc = server.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            clients.add(sc);
                        }
                    } else if(key.isReadable()) {
                        if(key.channel() instanceof SocketChannel client) {
                            this.receiveAndAnswer(client);
                        } else {
                            throw new RuntimeException("Unknown channel");
                        }
                    }
                }
                selector.selectedKeys().clear();
                Runtime.getRuntime().addShutdownHook(new Thread(collector::save));
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString() + "at server");
        } finally {
            for(SocketChannel sc : clients) {
                sc.close();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(collector::save));
        }
    }
    public void receiveAndAnswer(SocketChannel client) throws IOException, ClassNotFoundException {
        Request req = this.getRequest(client);
        invoker.setCommand(req);
        invoker.setCollection(collector);
        Response res = invoker.call(req);
        this.sendResponse(client, res);
        client.close();
    }
    public Request getRequest(SocketChannel client) throws IOException, ClassNotFoundException {
        data = ByteBuffer.allocate(1024);
        client.read(data);
        bais = new ByteArrayInputStream(data.array());
        in = new ObjectInputStream(bais);
        return (Request) in.readObject();
    }

    public void sendResponse(SocketChannel client, Response response) throws IOException {
        baos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(baos);
        out.writeObject(response);
        out.flush();
        client.write(ByteBuffer.wrap(baos.toByteArray()));
    }

}
