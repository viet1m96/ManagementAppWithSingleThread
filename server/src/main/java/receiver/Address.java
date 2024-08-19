package receiver;

public class Address {
    private final String street;
    public Address(String street) {
        this.street = street;
    }
    @Override
    public String toString() {return street;}
}
