package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class min_by_creation_dateCommand extends Command {
    public min_by_creation_dateCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.min_by_creation_date(req);
        return res;
    }
}
