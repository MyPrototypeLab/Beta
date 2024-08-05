package com.prototypelab.crud_android_mssql.main.Model;

public class Customer {

    private String Id;
    private String Name;
    private String Address;

    public Customer() {

    }

    public Customer(String id, String name, String address) {
        Id = id;
        Name = name;
        Address = address;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
