package faults;

public class DataNotCorrect extends Exception {
    public DataNotCorrect() {}
    public String Error() {
        return "The command was terminated due to invalid data in file";
    }
}
