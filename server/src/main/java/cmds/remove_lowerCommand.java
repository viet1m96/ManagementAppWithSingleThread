package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class remove_lowerCommand extends Command {
    public remove_lowerCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.remove_lower(req);
        return res;
    }
}
