package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class remove_by_idCommand extends Command {
    public remove_by_idCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.remove_by_id(req);
        return res;
    }
}
