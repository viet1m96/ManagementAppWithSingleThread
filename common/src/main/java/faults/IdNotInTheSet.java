package faults;

public class IdNotInTheSet extends Exception {
    public IdNotInTheSet() {}
    @Override
    public String toString() {return "Can not find the ID.";}
}
