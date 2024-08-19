package goods;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private final static long serialVersionUID = 2L;

    private List<String> container;

    public Response(List<String> container) {
        this.container = container;
    }

    public void printAll() {
        container.forEach(System.out::println);
    }

    public String getHead() {
        return container.get(0);
    }
}
