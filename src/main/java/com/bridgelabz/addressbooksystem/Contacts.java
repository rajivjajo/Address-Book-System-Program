package com.bridgelabz.addressbooksystem;

public class Contacts {
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private String Zip;
    private String phoneNumber;
    private String email;

    Contacts(){}

    public Contacts(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip='" + Zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
