package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class headCommand extends Command {
    public headCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.head(req);
        return res;
    }
}
