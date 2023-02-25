package com.bridgelabz.addressbooksystem;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookJson {
    public static void write(Map<String, AddressBook> addressBookMap) {
        String path = "D:\\Day35AddressBookSystem\\src\\main\\java\\com\\bridgelabz\\addressbooksystem\\Contacts.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path),addressBookMap);
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addressBookMap);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean read() {
        return false;
    }

//    public boolean read() {
//    }


}
