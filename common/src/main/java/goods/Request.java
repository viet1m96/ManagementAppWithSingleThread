package goods;

import num.TypeOfCmd;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private final static long serialVersionUID = 1L;
    private String cmd;
    private String ID;
    private String[] beside;

    public Request(String[] beside, String ID, String cmd) {
        this.beside = beside;
        this.ID = ID;
        this.cmd = cmd;
    }

    public String getID() {return ID;}
    public String getCmd() {return cmd;}
    public String[] getBeside() {return beside;}

}
