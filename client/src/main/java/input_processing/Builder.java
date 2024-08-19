package input_processing;

import faults.DataNotCorrect;
import num.Mode;

import java.io.BufferedReader;
import java.time.LocalDateTime;

public class Builder {
    public InputChecker checker;
    public Builder() {
        checker = new InputChecker();
    }

    public String[] build(Mode a, BufferedReader br) throws DataNotCorrect {
        String[] result = new String[10];
        for(int i = 0; i < 10; i++) result[i] = "";
        String tmp = "";
        result[1] = checker.inputFromUser(a, br, tmp, "string", "Name", "Must not be empty");
        result[2] = checker.inputFromUser(a, br, tmp, "number", "Coordinates X", "Must be a number and not be empty");
        result[3] = checker.inputFromUser(a, br, tmp, "number", "Coordinates Y", "Must be a number and not be empty");
        result[5] = checker.inputFromUser(a, br, tmp, "number", "Annual Turnover", "Must be a number and not be empty");
        result[6] = checker.inputFromUser(a, br, tmp, "string", "Full Name", "Must not be empty");
        result[7] = checker.inputFromUser(a, br, tmp, "number", "Number of Employees", "Must be a number and not be empty");
        result[8] = checker.inputFromUser(a, br, tmp, "type", "Type HERE one of these following types: PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY", "Must not be empty");
        result[9] = checker.inputFromUser(a, br, tmp, "string", "Address", "Must not be empty");
        result[4] = LocalDateTime.now().toString();
        for(int i = 1; i < 10; i++) {
            if(result[i].isEmpty()) throw new DataNotCorrect();
        }
        return result;
    }
}
