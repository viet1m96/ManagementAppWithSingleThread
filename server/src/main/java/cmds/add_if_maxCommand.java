package cmds;

import goods.Request;
import goods.Response;
import receiver.CollectionManager;

public class add_if_maxCommand extends Command {
    public add_if_maxCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.add_if_max(req);
        return res;
    }
}
