package cmds;

import goods.Request;
import goods.Response;
import receiver.CollectionManager;

public abstract class Command {
    private CollectionManager collectionManager;
    abstract public void setCollection(CollectionManager store);
    abstract public Response execute(Request request);
}
