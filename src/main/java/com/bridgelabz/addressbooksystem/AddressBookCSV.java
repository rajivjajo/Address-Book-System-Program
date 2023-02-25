package com.bridgelabz.addressbooksystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class AddressBookCSV {
    public static void write(Map<String, AddressBook> data) throws IOException {

        try {
            FileWriter writer = new FileWriter("mapdata.csv");
            writer.append("First Name,Last Name,Address,City,State,Zip Code,PhoneNumber, Email\n");
            for (Map.Entry<String, AddressBook> entry : data.entrySet()) {
                String key = entry.getKey();
                AddressBook value = entry.getValue();
                for (Contacts contact : value.getContactsList()) {
                    writer.append(entry.getKey()).append(",")
                            .append(contact.getFirstName()).append(",")
                            .append(contact.getLastName()).append(",")
                            .append(contact.getAddress()).append(",")
                            .append(contact.getCity()).append(",")
                            .append(contact.getState()).append(",")
                            .append(contact.getZip()).append(",")
                            .append(contact.getPhoneNumber()).append(",")
                            .append(contact.getEmail()).append("\n");
                }
            }
            writer.flush();
            writer.close();
            System.out.println("Map data written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, AddressBook> read() throws IOException {
        Map<String, AddressBook> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("mapdata.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String key = fields[0];
                    String name = fields[1];
                    AddressBook addressBook = data.get(key);
                    if (addressBook == null) {
                        addressBook = new AddressBook();
                        data.put(key, addressBook);
                    }
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return data;
    }
}
