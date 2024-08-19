package cmds;

import goods.Request;
import goods.Response;
import receiver.*;
public class checkIDCommand extends Command {
    public checkIDCommand() {}
    private CollectionManager store;
    @Override
    public void setCollection (CollectionManager store){
        this.store = store;
    }
    @Override
    public Response execute(Request request) {
        Response res = null;
        res = store.checkID(request);
        return res;
    }
}
