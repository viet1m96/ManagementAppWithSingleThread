package receiver;

import java.time.LocalDateTime;
import java.util.*;

import file_processing.*;
import goods.Request;
import goods.Response;

public class CollectionManager {
    private Deque<Organization> collection;
    private Set<String> IDCollection;
    private LocalDateTime firstMoment;
    private CSVReader reader;
    private CSVWriter writer;

    public CollectionManager(String fileName) {
        IDCollection = new HashSet<>();
        collection = new ArrayDeque<>();
        firstMoment = LocalDateTime.now();
        reader = new CSVReader(fileName);
        writer = new CSVWriter(fileName);
        this.loadData();
    }

    public void loadData() {
        Queue<String[]> data = reader.getDataFromFile();
        while (!data.isEmpty()) {
            Organization org = new Organization();
            org.setOrganization(data.poll());
            collection.add(org);
        }
    }

    public Response add(Request request) {
        String[] inp = request.getBeside();
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        while(IDCollection.contains(String.valueOf(n))) {
            n = 100000 + rnd.nextInt(900000);
        }
        IDCollection.add(String.valueOf(n));
        inp[0] = String.valueOf(n);

        Organization org = new Organization();
        org.setOrganization(inp);
        collection.add(org);
        List<String> ls = new ArrayList<>();
        ls.add("The add command was executed successfully.");
        return new Response(ls);
    }

    public Response clear(Request request) {
        collection.clear();
        List<String> ls = new ArrayList<>();
        ls.add("The clear command was executed successfully.");
        return new Response(ls);
    }

    public Response head(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("The collection is empty.");
        } else {
            Organization org = collection.peek();
            ls = org.getEverythingOfAnElement();
        }
        ls.add("The head command was executed successfully.");
        return new Response(ls);
    }

    public Response info(Request request) {
        List<String> ls = new ArrayList<>();
        ls.add("Type of DS: Queue");
        ls.add("Moment of Initialization: " + firstMoment.toString());
        ls.add("Current number of elements: " + collection.size());
        ls.add("Info command was executed successfully");
        return new Response(ls);
    }

    public Response min_by_creation_date(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("The collection is empty.");
        } else {
            Organization org = collection.peek();
            ls = org.getEverythingOfAnElement();
        }
        ls.add("The min_by_creation_date command was executed successfully.");
        return new Response(ls);
    }

    public Response print_unique_postal_address(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("The collection is empty.");
        } else {
            collection.forEach(org -> ls.add(org.getAddress()));
        }
        ls.add("The print_unique_postal_address command was executed successfully.");
        return new Response(ls);
    }

    public Response show(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("The collection is empty.");
        } else {
            collection.forEach(org -> ls.addAll(org.getEverythingOfAnElement()));
        }
        ls.add("The show command was executed successfully.");
        return new Response(ls);
    }

    public Response add_if_max(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            Organization org = new Organization();
            org.setOrganization(request.getBeside());
            collection.add(org);
            ls.add("The element was added to the collection.");
        } else {
            Float mx = 0.0F;
            for(Organization org : collection) {
                mx = Math.max(mx, org.getAnnualTurnover());
            }
            String[] inp = request.getBeside();
            if(Float.compare(mx, Float.parseFloat(inp[0])) < 0) {
                Organization org = new Organization();
                org.setOrganization(inp);
                collection.add(org);
                ls.add("The element was added to the collection.");
            } else {
                ls.add("The element was not added to the collection.");
            }
        }
        return new Response(ls);
    }

    public Response remove_lower(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("Nothing to remove.");
        } else {
            Float mx = Float.parseFloat(request.getBeside()[5]);
            collection.removeIf(org -> Float.compare(mx, org.getAnnualTurnover()) > 0);
        }
        ls.add("The remove_lower command was executed successfully.");
        return new Response(ls);
    }

    public Response filter_starts_with_full_name(Request request) {
        List<String> ls = new ArrayList<>();
        if(collection.isEmpty()) {
            ls.add("The collection is empty.");
        } else {
            for(Organization org : collection) {
                if(org.getFullName().contains(request.getBeside()[0])) {
                    ls.addAll(org.getEverythingOfAnElement());
                }
            }
        }
        ls.add("The filter_starts_with_full_name command was executed successfully.");
        return new Response(ls);
    }

    public Response update(Request request) {
        List<String> ls = new ArrayList<>();
        String id = request.getID();
        for(Organization org : collection) {
            if(String.valueOf(org.getId()).equals(id)) {
                org.setOrganization(request.getBeside());
                break;
            }
        }
        ls.add("The update command was executed successfully.");
        return new Response(ls);
    }

    public Response remove_by_id(Request request) {
        List<String> ls = new ArrayList<>();
        String id = request.getID();
        if(IDCollection.contains(id)) {
            collection.removeIf(org -> String.valueOf(org.getId()).equals(id));
            IDCollection.removeIf(l -> l.equals(id));
        } else {
            ls.add("ID does not exist.");
        }
        ls.add("The remove_by_id command was executed successfully.");
        return new Response(ls);
    }

    public Response checkID(Request request) {
        List<String> ls = new ArrayList<>();
        if(IDCollection.contains(request.getID())) {
            ls.add("ok");
        } else {
            ls.add("no");
        }
        return new Response(ls);
    }

    public Response help(Request request) {
        List<String> ls = new ArrayList<>();
        ls.add("help : display help on available commands\n" +
                "info : display information about the collection (type, initialization date, number of elements, etc.) to standard output\n" +
                "show : display all elements of the collection as strings to standard output\n" +
                "add {element} : add a new element to the collection\n" +
                "update id {element} : update the value of the collection element whose id is equal to the specified one\n" +
                "remove_by_id id : remove an element from the collection by its id\n" +
                "clear : clear the collection\n" +
                "execute_script file_name : read and execute a script from the specified file. The script contains commands in the same form in which the user enters them interactively.\n" +
                "exit : exit the program (without saving to file)\n" +
                "head : print the first element of the collection\n" +
                "add_if_max {element} : add a new element to the collection if its value is greater than the value of the largest element of this collection\n" +
                "remove_lower {element} : remove all elements from the collection that are smaller than the specified\n" +
                "min_by_creation_date : print any object from the collection whose creationDate field value is the minimum\n" +
                "filter_starts_with_full_name fullName : print elements whose fullName field value starts with the specified substring\n" +
                "print_unique_postal_address : print the unique values \u200B\u200Bof the postalAddress field of all elements in the collection");
        return new Response(ls);
    }

    public void save() {
        writer.setPointer();
        for (Organization org : collection) {
            writer.writeToCSV(org.packagingToString());
        }
        writer.shutDown();
    }
}
