package com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.util.*;


public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBookMainObj = new AddressBook();
        Map<String,AddressBook> addressBookMap = new HashMap<>();
        System.out.println("Welcome to Address Book System");
        while(true){
            System.out.println("1. Add new Address Book");
            System.out.println("2. Edit Address Book");
            System.out.println("3. Display Address Book Names");
            System.out.println("4. Delete Address Book ");
            System.out.println("5. Search Person ");
            System.out.println("6. View Persons  ");
            System.out.println("7. Count Persons");
            System.out.println("8. Writing");
            System.out.println("9. Reading");
            System.out.println("10. CSV Write");
            System.out.println("11. CSV Read");
            System.out.println("12. Exit");
            System.out.println("Enter your choice: ");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Name of new Address Book: ");
                    String addressBookName = sc.next();
                    addressBookMap.put(addressBookName, new AddressBook());
                    addressBookMainObj.addressBookOptions(addressBookMap.get(addressBookName));
                    break;
                case 2:
                    Scanner sce = new Scanner(System.in);
                    System.out.println("List of available Address Book : ");
                    Set keys = addressBookMap.keySet();
                    Iterator i = keys.iterator();
                    while (i.hasNext()){
                        System.out.println(i.next());
                    }
                    System.out.println("Enter Address Book name you want to Open : ");
                    String name = sce.nextLine();
                    System.out.println("Current Address Book is : " + name);
                    addressBookMainObj.addressBookOptions(addressBookMap.get(name));
                    break;
                case 3:
                    if (addressBookMap.size() == 0) {
                        System.out.println("Address Book is Empty");
                    } else {
                        System.out.println("Address Books List");
                        int j = 0;
                        for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
                            String k = entry.getKey();
                            System.out.println(j + ") " + k);
                        }
                    }
                    break;
                case 4:
                    Scanner s4 = new Scanner(System.in);
                    if (addressBookMap.size() != 0) {
                        for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
                            String k = entry.getKey();
                            System.out.println("Address Book Name : " + k);
                        }
                        System.out.println("Enter Address Book name which you want Delete");
                        String addressBookNamed = s4.next();
                        for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
                            String k = entry.getKey();
                            if (k.equalsIgnoreCase(addressBookNamed)) {
                                addressBookMap.remove(k);
                                System.out.println("Address Book with name  " + addressBookNamed + " deleted successfully");
                                break;
                            }
                        }
                    }
                    else {
                        System.out.println("No Address Book exist in the Record" );
                    }
                    break;
                case 5:
                    System.out.println("Welcome to the search option:");
                    addressBookMainObj.searchByOptions();
                    break;
                case 6:
                    System.out.println("Welcome to view By Option:");
                    addressBookMainObj.viewByOption();
                    break;
                case 7:
                    System.out.println("Welcome to the couter:");
                    addressBookMainObj.countByOption();
                    break;
                case 8:
                    AddressBookFileIO addressBookFileIO = new AddressBookFileIO();
                    addressBookFileIO.writeData(addressBookMap);
                    break;
                case 9:
                    AddressBookFileIO addressBookFileIO2 = new AddressBookFileIO();
                    System.out.println(addressBookFileIO2.readData());
                    break;
                case 10:
                    AddressBookCSV addressBookCSV = new AddressBookCSV();
                    addressBookCSV.write(addressBookMap);
                    break;
                case 11:
                    AddressBookCSV addressBookCSVr = new AddressBookCSV();
                    System.out.println(addressBookCSVr.read());
                    break;
                case 12:
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
