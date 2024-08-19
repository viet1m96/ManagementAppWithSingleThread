package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class clearCommand extends Command {
    public clearCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.clear(req);
        return res;
    }
}
