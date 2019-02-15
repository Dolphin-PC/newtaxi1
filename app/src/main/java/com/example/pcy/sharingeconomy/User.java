package com.example.pcy.sharingeconomy;

public class User {

    public String username;
    public String password;
    public String email;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public void setUsername(){this.username=username;}
    public void setEmail(){this.email=email;}
    public void setPassword(){this.password=password;}

}