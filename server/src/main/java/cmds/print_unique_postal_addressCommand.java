package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class print_unique_postal_addressCommand extends Command {
    public print_unique_postal_addressCommand() {}

    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request req) {
        Response res = null;
        res = store.print_unique_postal_address(req);
        return res;
    }
}
