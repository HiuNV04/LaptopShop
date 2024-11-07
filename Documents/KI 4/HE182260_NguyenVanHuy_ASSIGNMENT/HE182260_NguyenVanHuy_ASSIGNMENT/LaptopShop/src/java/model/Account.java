/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DungTT
 */
public class Account {

    private int account_id;
    private String fullname, phone, email, username, password;
    private int role_id, theaterID;

    public Account(int account_id, String fullname, String phone, String email, String username, String password, int role_id, int theaterID) {
        this.account_id = account_id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.theaterID = theaterID;
    }

    public Account(String fullname, String phone, String email, String username, String password, int role_id, int theaterID) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.theaterID = theaterID;
    }
    
    

    public int getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(int theaterID) {
        this.theaterID = theaterID;
    }
    




    public Account() {
    }

//    public Account(int account_id, String fullname, String phone, String email, String username, String password, int role_id, int theaterID) {
//        this.account_id = account_id;
//        this.fullname = fullname;
//        this.phone = phone;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.role_id = role_id;
//        this.theaterID = theaterID;
//    }

//    public Account(String fullname, String phone, String email, String username, String password, int role_id, int theaterID) {
//        this.fullname = fullname;
//        this.phone = phone;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.role_id = role_id;
//        this.theaterID = theaterID;
//    }

    @Override
    public String toString() {
        return "Account{" + "account_id=" + account_id + ", fullname=" + fullname + ", phone=" + phone + ", email=" + email + ", username=" + username + ", password=" + password + ", role_id=" + role_id + ", theaterID=" + theaterID + '}';
    }
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

//    public int getTheaterID() {
//        return theaterID;
//    }
//
//    public void setTheaterID(int theaterID) {
//        this.theaterID = theaterID;
//    }
}
