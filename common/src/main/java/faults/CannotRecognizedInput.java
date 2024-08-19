package faults;

public class CannotRecognizedInput extends Exception{
    public CannotRecognizedInput() {}
    @Override
    public String toString() {
        return "The input was not recognized, please type again.";
    }
}
