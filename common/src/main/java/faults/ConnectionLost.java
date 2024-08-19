package faults;

public class ConnectionLost extends Exception {
    public ConnectionLost() {}
    public String toSTring() {return "The program was terminated due to connection lost";}
}
