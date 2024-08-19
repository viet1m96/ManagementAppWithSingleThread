package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class updateCommand extends Command {
    public updateCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.update(req);
        return res;
    }
}
