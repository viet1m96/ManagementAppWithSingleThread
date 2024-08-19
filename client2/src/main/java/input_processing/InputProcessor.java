package input_processing;

import faults.CannotRecognizedInput;
import faults.ConnectionLost;
import faults.DataNotCorrect;
import goods.Request;
import goods.Response;
import network.NioClient;
import num.Mode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;


public class InputProcessor {
    public Builder builder;
    public Deque<Request> requests;
    private NioClient client;
    public InputProcessor() {
        client = new NioClient();
        requests = new ArrayDeque<>();
        builder = new Builder();
    }

    public void add(Mode a, String s, BufferedReader br) {
        try {
            String[] element = builder.build(a, br);
            requests.add(new Request(element, "", "add"));
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        }
    }

    public void clear(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "clear"));
    }

    public void head(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "head"));
    }

    public void info(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "info"));
    }

    public void min_by_creation_date(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "min_by_creation_date"));
    }

    public void print_unique_postal_address(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "print_unique_postal_address"));
    }

    public void show(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "show"));
    }

    public void add_if_max(Mode a, String s, BufferedReader br) {
        try {
            String[] element = builder.build(a, br);
            requests.add(new Request(element, "", "add_if_max"));
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        }
    }

    public void remove_lower(Mode a, String s, BufferedReader br) {
        try {
            String[] element = builder.build(a, br);
            requests.add(new Request(element, "", "remove_lower"));
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        }
    }

    public void filter_starts_with_full_name(Mode a, String s, BufferedReader br) {
        String[] ls = new String[1];
        ls[0] = s;
        requests.add(new Request(ls, "", "filter_starts_with_full_name"));
    }

    public void update(Mode a, String s, BufferedReader br) throws ConnectionLost {
        try {
            String id = builder.checker.getString(s);
            if(!client.buildAndConnect()) {
                System.out.println("Connection lost");
                return;
            }
            client.sendARequest(new Request(null, id, "checkID"));
            Response res = client.getAResponse();
            if(res.getHead().equals("ok")) {
                String[] element = builder.build(a, br);
                element[0] = id;
                requests.add(new Request(element, id, "update"));
            } else {
                throw new DataNotCorrect();
            }
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        } catch (IOException e) {
            System.out.println(e.toString() + " at update");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void remove_by_id(Mode a, String s, BufferedReader br) {
        try {
            String id = builder.checker.getString(s);
            requests.add(new Request(null, id, "remove_by_id"));
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        }
    }

    public void help(Mode a, String s, BufferedReader br) {
        requests.add(new Request(null, "", "help"));
    }

    public void execute_script(Mode a, String s, BufferedReader br) {
        try {
            String fileName = builder.checker.getString(s);
            BufferedReader wr = new BufferedReader(new FileReader(fileName));
            String line = "";
            while((line = wr.readLine()) != null) {
                String cmd = builder.checker.checkCommand(line);
                Method method = this.getClass().getDeclaredMethod(cmd, Mode.class, String.class, BufferedReader.class);
                method.invoke(this, Mode.FILE, line, wr);
            }
        } catch (DataNotCorrect e) {
            System.out.println(e.Error());
        } catch (IOException | CannotRecognizedInput | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString() + " at execute_script");
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found in script");
        }
    }

}
