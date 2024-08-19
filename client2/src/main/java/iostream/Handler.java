package iostream;

import faults.CannotRecognizedInput;
import goods.Response;
import input_processing.InputProcessor;
import network.NioClient;
import num.Mode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Handler {
    private BufferedReader br;
    private NioClient client;
    private InputProcessor pro;
    public Handler() {
        System.out.println("Hello! Welcome to my Application!");
        System.out.println("Please type 'help' to read the instruction or type 'exit' to shut down the program.");
        br = new BufferedReader(new InputStreamReader(System.in));
        pro = new InputProcessor();
        client = new NioClient();
    }

    public void run() {
        while(true) {
            try {
                String str = br.readLine();
                if(str.equals("exit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                String cmd = pro.builder.checker.checkCommand(str);
                if(!transfer(cmd, str)) {
                    System.out.println("The program was terminated due to connection lost.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CannotRecognizedInput e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean transfer(String cmd, String str) {
        try {
            Method method = pro.getClass().getDeclaredMethod(cmd, Mode.class, String.class, BufferedReader.class);
            method.invoke(pro, Mode.KBOARD, str, br);
            while(!pro.requests.isEmpty()) {
                if(!client.buildAndConnect()) return false;
                client.sendARequest(pro.requests.poll());
                Response res = client.getAResponse();
                res.printAll();
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
