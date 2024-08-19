package cmds;

import goods.Request;
import goods.Response;
import receiver.CollectionManager;

public class addCommand extends Command {
    public addCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.add(req);
        return res;
    }
}
