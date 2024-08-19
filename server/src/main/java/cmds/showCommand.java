package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class showCommand extends Command {
    public showCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.show(req);
        return res;
    }
}
