/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1.models;

/**
 *
 * @author JESUS-CHRIST
 */
public class User {

    private String userNom;
    private String userPrenom;
    private String email;
    private String phone;
    private String role;

    public User(String userNom, String userPrenom, String email, String phone, String role) {
        this.userNom = userNom;
        this.userPrenom = userPrenom;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User() {

        this.userNom = "";
        this.userPrenom = "";
        this.email = "";
        this.phone = "";
        this.role = "";

    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
