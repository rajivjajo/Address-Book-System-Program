package com.bridgelabz.addressbooksystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class AddressBookFileIO {
    public void writeData(Map<String, AddressBook> addressBook) {
        File file = new File("D:\\AddressBookServices\\src\\main\\java\\com\\bridgelabz\\addressbooksystem\\Contacts.txt");
        BufferedWriter bf = null;;
        try {
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, AddressBook> entry : addressBook.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,  String> readData() {
        Map<String, String> mapFileContents = new HashMap<>();
        BufferedReader br = null;
        try {
            File file = new File("D:\\AddressBookServices\\src\\main\\java\\com\\bridgelabz\\addressbooksystem\\Contacts.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String bookName = parts[0].trim();
                String fname = parts[1].trim();
                mapFileContents.put(bookName, fname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }
        return mapFileContents;
    }


}
