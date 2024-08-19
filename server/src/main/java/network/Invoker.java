package network;

import cmds.*;
import goods.Request;
import goods.Response;
import receiver.CollectionManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Command command;
    private Map<String, Command> commands;
    public Invoker(){
        commands = new HashMap<>();
        commands.put("add", new addCommand());
        commands.put("add_if_max", new add_if_maxCommand());
        commands.put("clear", new clearCommand());
        commands.put("head", new headCommand());
        commands.put("help", new helpCommand());
        commands.put("info", new infoCommand());
        commands.put("min_by_creation_date", new min_by_creation_dateCommand());
        commands.put("print_unique_postal_address", new print_unique_postal_addressCommand());
        commands.put("remove_lower", new remove_lowerCommand());
        commands.put("show", new showCommand());
        commands.put("update", new updateCommand());
        commands.put("remove_by_id", new remove_by_idCommand());
        commands.put("filter_starts_with_full_name", new filter_starts_with_full_nameCommand());
        commands.put("checkID", new checkIDCommand());
    }

    public void setCommand(Request req) {
        command = commands.get(req.getCmd());
    }
    public void setCollection(CollectionManager store) {
        command.setCollection(store);
    }
    public Response call(Request req) {return command.execute(req);}
}
