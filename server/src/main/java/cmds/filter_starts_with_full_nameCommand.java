package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class filter_starts_with_full_nameCommand extends Command {
    public filter_starts_with_full_nameCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.filter_starts_with_full_name(req);
        return res;
    }
}
