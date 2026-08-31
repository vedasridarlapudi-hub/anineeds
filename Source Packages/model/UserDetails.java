package model;

public class UserDetails {
    private String phone;
    private String address;
    
    // Constructor
    public UserDetails(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }
    
    // Getters
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}