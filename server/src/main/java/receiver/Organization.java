package receiver;

import num.OrganizationType;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private int id;// mine
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;// mine
    private Float annualTurnover;
    private String fullName;
    private int employeesCnt;
    private OrganizationType type;
    private Address postalAddress;

    public void setOrganization(String[] inp) {
        id = Integer.parseInt(inp[0]);
        name = inp[1];
        coordinates = new Coordinates(Double.parseDouble(inp[2]), Integer.parseInt(inp[3]));
        creationDate = java.time.LocalDateTime.parse(inp[4]);
        annualTurnover = Float.parseFloat(inp[5]);
        fullName = inp[6];
        employeesCnt = Integer.parseInt(inp[7]);
        type = OrganizationType.valueOf(inp[8]);
        postalAddress = new Address(inp[9]);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return creationDate.toString();
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeesCnt() {
        return employeesCnt;
    }

    public String getAddress() {
        return postalAddress.toString();
    }

    public void changeValOfTurnover(float inp) {
        annualTurnover = inp;
    }

    public List<String> getEverythingOfAnElement() {
        List<String> ls = new ArrayList<>();
        ls.add("ID: " + id);
        ls.add("Name: " + name);
        ls.add("Coordinates X: " + coordinates.getX());
        ls.add("Coordinates Y: " + coordinates.getY());
        ls.add("Creation Date: " + creationDate.toString());
        ls.add("Annual Turnover: " + annualTurnover);
        ls.add("Full Name: " + fullName);
        ls.add("Employee Count: " + employeesCnt);
        ls.add("Type: " + type.toString());
        ls.add("Address: " + postalAddress.toString());
        ls.add("/n");
        return ls;
    }

    public String[] packagingToString() {
        String[] data = new String[10];
        data[0] = Integer.toString(id);
        data[1] = name;
        data[2] = coordinates.getX().toString();
        data[3] = coordinates.getY().toString();
        data[4] = creationDate.toString();
        data[5] = annualTurnover.toString();
        data[6] = fullName;
        data[7] = Integer.toString(employeesCnt);
        data[8] = type.toString();
        data[9] = postalAddress.toString();
        return data;
    }

}
