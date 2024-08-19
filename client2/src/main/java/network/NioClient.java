package network;

import goods.Request;
import goods.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    private SocketChannel skChannel;
    private ByteBuffer data;
    private ByteArrayInputStream bais;
    private ByteArrayOutputStream baos;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final int PORT = 4999;

    public NioClient() {}

    public boolean buildAndConnect() throws IOException {
        try {
            skChannel = SocketChannel.open();
            skChannel.connect(new InetSocketAddress("localhost", PORT));
        } catch (ConnectException e) {
            return false;
        }
        return true;
    }

    public void sendARequest(Request request) throws IOException {
        baos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(baos);
        out.writeObject(request);
        out.flush();
        skChannel.write(ByteBuffer.wrap(baos.toByteArray()));
    }

    public Response getAResponse() throws IOException, ClassNotFoundException {
        data = ByteBuffer.allocate(1 << 20);
        skChannel.read(data);
        bais = new ByteArrayInputStream(data.array());
        in = new ObjectInputStream(bais);
        return (Response)in.readObject();
    }

}
