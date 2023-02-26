package com.bridgelabz.addressbooksystem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.sql.*;

public class AddressBook {
    public static List<Contacts> contactsList = new ArrayList<>();
    public static Map<String, Contacts> contactNamesMap = new HashMap<>();
    public static Map<String, Contacts> contactCitiesMap = new HashMap<>();
    public static Map<String, Contacts> contactStatesMap = new HashMap<>();
    static AddressBook addressBook = new AddressBook();

    public static List<Contacts> getContactsList() {
        return contactsList;
    }

    public static void setContactsList(List<Contacts> contactsList) {
        AddressBook.contactsList = contactsList;
    }

    public void addressBookOptions(AddressBook addressBook) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Address Book Contact Option ");
            System.out.println("1. Add contact details");
            System.out.println("2. Edit contact details");
            System.out.println("3. Delete contact details");
            System.out.println("4. Show contacts details");
            System.out.println("5. Sort Address Book");
            System.out.println("6. Back to main menu");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (addContact(readContact()))
                        System.out.println("Cotact Added Successfully....!");
                    else
                        System.out.println("Contact Already Exist....!");
                    break;
                case 2:
                    System.out.println("Enter First name to edit contact: ");
                    String name = sc.nextLine();
                    List<Contacts> equalName = searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found....!");
                    else if (equalName.size() == 1) {
                        editContact(equalName.get(0), readContact());
                        System.out.println("Contact data modified....!");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter index to edit : ");
                        int i = sc.nextInt();
                        editContact(equalName.get(i), readContact());
                        System.out.println("Contact Modified....!");
                    }
                    break;
                case 3:
                    System.out.println("Enter First name to delete contact: ");
                    name = sc.nextLine();
                    equalName = searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found.....!");
                    else if (equalName.size() == 1) {
                        deleteContact(equalName.get(0));
                        System.out.println("Contact data deleted....!");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter an index to delete");
                        int index = sc.nextInt();
                        deleteContact(equalName.get(index));
                        System.out.println("Cotact data deleted....!");
                    }
                    break;
                case 4:
                    System.out.println(toString());
                    break;
                case 5:
                    sortByOption();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    public List<Contacts> sortBy(Function<? super Contacts, ? extends String> key) {
        return contactsList.stream().sorted(Comparator.comparing(key)).collect(Collectors.toList());
    }

    public static void sortByOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. By First name");
        System.out.println("2. By Last name");
        System.out.println("3. By City");
        System.out.println("4. By State");
        System.out.println("5. By Zip");
        System.out.println("6. Back");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                addressBook.sortBy(Contacts::getFirstName).forEach(System.out::println);
                break;
            case 2:
                addressBook.sortBy(Contacts::getLastName).forEach(System.out::println);
                break;
            case 3:
                addressBook.sortBy(Contacts::getCity).forEach(System.out::println);
                break;
            case 4:
                addressBook.sortBy(Contacts::getState).forEach(System.out::println);
                break;
            case 5:
                addressBook.sortBy(Contacts::getZip).forEach(System.out::println);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    @Override
    public String toString() {
        if (contactsList.isEmpty())
            return "No contacts found!";
        String result = new String();
        for (int i = 0; i < contactsList.size(); i++) {
            result += " " + contactsList.get(i);
        }
        return result;
    }

    public static boolean deleteContact(Contacts contacts) {
        contactsList.remove(contacts);
        return true;
    }

    public static boolean editContact(Contacts current, Contacts editedContact) {
        if (!contactsList.contains(current)) {
            return false;
        }
        contactsList.remove(current);
        contactsList.add(editedContact);
        return true;
    }

    public static List<Contacts> searchByName(String name) {
        return contactsList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public static boolean addContact(Contacts contact) {
        List<Contacts> checkByName = searchByName(contact.getFirstName());
        for (Contacts equalName : checkByName) {
            if (equalName.equals(contact))
                return false;
        }
        contactsList.add(contact);
        contactNamesMap.put(contact.getFirstName(), contact);
        contactCitiesMap.put(contact.getCity(), contact);
        contactStatesMap.put(contact.getState(), contact);
        return true;

    }

    public static Contacts readContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Contact First Name");
        String firstName = scanner.next();

        System.out.println("Enter Contact Last Name");
        String lastName = scanner.next();

        System.out.println("Enter Contact Address Details");
        String address = scanner.next();

        System.out.println("Enter Contact City Name");
        String city = scanner.next();

        System.out.println("Enter Contact State Name");
        String state = scanner.next();

        System.out.println("Enter Contact Zip");
        String zip = scanner.next();

        System.out.println("Enter Contact Phone Number");
        String phoneNumber = scanner.next();

        System.out.println("Enter Contact Email");
        String email = scanner.next();

        return new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }

    public static void searchByOptions() {
        Scanner sbo = new Scanner(System.in);
        System.out.println("1. Search By Name");
        System.out.println("2. Search By City");
        System.out.println("3. Search By State");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sbo.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = sbo.next();
                contactsList.forEach(book -> addressBook.searchByName(name).forEach(System.out::println));
                break;
            case 2:
                System.out.print("Enter city: ");
                String city = sbo.next();
                contactsList.forEach(book -> addressBook.searchByCity(city).forEach(System.out::println));
                break;
            case 3:
                System.out.print("Enter state: ");
                String state = sbo.next();
                contactsList.forEach(book -> addressBook.searchByState(state).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static List<Contacts> searchByCity(String city) {
        return contactsList.stream().filter(person -> person.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
    }

    //method for searching contact by State
    public static List<Contacts> searchByState(String state) {
        return contactsList.stream().filter(person -> person.getState().equalsIgnoreCase(state)).collect(Collectors.toList());
    }

    public static void viewByName(Map<String, Contacts> nameHashMap) {
        contactNamesMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    //method for view contact by city
    public static void viewByCity(Map<String, Contacts> cityHashMap) {
        contactCitiesMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    //method for view contact by State
    public static void viewByState(Map<String, Contacts> stateHashMap) {
        stateHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. View By Name");
        System.out.println("2. View By City");
        System.out.println("3. View By State");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                viewByName(contactNamesMap);
                break;
            case 2:
                viewByCity(contactCitiesMap);
                break;
            case 3:
                viewByState(contactStatesMap);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static void countByOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Count City ");
        System.out.println("2. Count State");
        System.out.println("3. Back ");
        System.out.println("Enter Your Choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Map<String, Long> countCity = contactsList.stream().collect(Collectors.groupingBy(e -> e.getCity(), Collectors.counting()));
                System.out.println(countCity + "\n");
                break;
            case 2:
                Map<String, Long> countState = contactsList.stream().collect(Collectors.groupingBy(e -> e.getState(), Collectors.counting()));
                System.out.println(countState + "\n");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public void readDatabaseData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
            // here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from AddressBook");
            while (rs.next())
                System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " +rs.getString(4) + " : " +rs.getString(5) + " : " +rs.getString(6) + " : " +rs.getString(7) + " : " +rs.getString(8) + " : " +rs.getString(9) + " : " +rs.getString(10));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContact() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
            PreparedStatement statement = connection.prepareStatement("UPDATE AddressBook SET PHONENUMBER = '0000000000' WHERE FIRSTNAME = PRASANTH;");
            boolean response = statement.execute();
            if (response) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1));
            } else {
                int count = statement.getUpdateCount();
                System.out.println(count);
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void contactsInBetweenDates() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Address_Book WHERE date_added between cast('2005-12-11' as date) and cast('2020-12-06' as date)");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " +rs.getString(4) + " : " +rs.getString(5) + " : " +rs.getString(6) + " : " +rs.getString(7) + " : " +rs.getString(8) + " : " +rs.getString(9) + " : " +rs.getString(10));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void countByCityOrState() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Count By City");
        System.out.println("2 Count By State");
        System.out.println("Choose option");
        int number = scanner.nextInt();
        switch(number){
            case 1:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT CITY, count(*) AS count FROM AddressBook GROUP BY CITY");
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String city = rs.getString("CITY");
                        double countContacts = rs.getInt("count");
                        System.out.println(city + " Total Contacts: " + countContacts);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT STATE, count(*) AS count FROM AddressBook GROUP BY STATE");
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String city = rs.getString("STATE");
                        double countContacts = rs.getInt("count");
                        System.out.println(city + " Total States: " + countContacts);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void addContacts() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Address_Book", "root", "d11cpk1211");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO AddressBook(FIRSTNAME,LASTNAME,ADDRESS,CITY,STATE,ZIP,PHONENUMBER,EMAIL,BOOKNAME,BOOKTYPE) VALUES ('John', 'Doe', '123 Main St', 'Anytown', 'CA', '12345', '5555551234', 'john.doe@example.com', 'Friend', 'Friend')");
            boolean response = statement.execute();
            if (response) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1));
            } else {
                int count = statement.getUpdateCount();
                System.out.println(count);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
