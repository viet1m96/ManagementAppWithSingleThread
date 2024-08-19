package cmds;

import goods.Request;
import goods.Response;
import receiver.CollectionManager;

public class helpCommand extends Command {
    public helpCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.help(req);
        return res;
    }
}
