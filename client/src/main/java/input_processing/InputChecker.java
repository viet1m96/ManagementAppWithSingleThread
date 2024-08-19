package input_processing;

import faults.CannotRecognizedInput;
import faults.DataNotCorrect;
import num.Mode;
import num.TypeOfCmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InputChecker {
    private Set<String> commands;
    
    public InputChecker() {
        commands = new HashSet<>();
        commands.add("add");
        commands.add("add_if_max");
        commands.add("clear");
        commands.add("exit");
        commands.add("head");
        commands.add("help");
        commands.add("info");
        commands.add("min_by_creation_date");
        commands.add("print_unique_postal_address");
        commands.add("remove_lower");
        commands.add("show");
        commands.add("execute_script");
        commands.add("filter_starts_with_full_name");
        commands.add("update");
        commands.add("remove_by_id");
    }

    public String checkCommand(String command) throws CannotRecognizedInput {
        for(String str : commands) {
            if(str.equals(command)) {
                return str;
            } else if(command.contains(str)) {
                String tmp = command.substring(0, str.length());
                if(tmp.equals(str)) return str;
            }
        }
        throw new CannotRecognizedInput();
    }

    public boolean checkString(String str) {
        return !str.isEmpty();
    }

    public boolean checkNumber(String str) {
        if(str.isEmpty()) return false;
        if(str.charAt(0) == '.' || str.charAt(str.length()-1) == '.') return false;
        int cnt = 0;
        for(int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if(tmp == '.') cnt++;
            if(cnt > 1) return false;
            if((tmp < '0' || tmp > '9') && tmp != '.') return false;
        }
        return true;
    }

    public boolean checkType(String str) {
        if(str.isEmpty()) return false;
        return str.equals("PUBLIC") || str.equals("GOVERNMENT") || str.equals("PRIVATE_LIMITED_COMPANY") || str.equals("TRUST") || str.equals("OPEN_JOINT_STOCK_COMPANY");
    }

    public String getString(String s) throws DataNotCorrect {
        String str = "";
        if(!s.contains(" ")) throw new DataNotCorrect();
        int idx = s.indexOf(' ');
        str = s.substring(idx);
        str = str.replaceAll("\\s", "");
        return str;
    }


    public String inputFromUser(Mode a, BufferedReader br, String tmp, String typeOf, String notice, String condition) {
        while(tmp.isEmpty()) {
            if(a == Mode.KBOARD) System.out.println(notice + '(' + condition + ')');
            try {
                String str = br.readLine();
                boolean check;
                if(typeOf.equals("number")) {
                    check = checkNumber(str);
                } else if(typeOf.equals("string")) {
                    check = checkString(str);
                } else {
                    check = checkType(str);
                }
                if(check) {
                    tmp = str;
                    break;
                } else {
                    throw new IOException();
                }
            } catch(IOException e) {
                System.out.println("The input was invalid.");
            }
            if(a == Mode.FILE) break;
        }
        return tmp;
    }
}
