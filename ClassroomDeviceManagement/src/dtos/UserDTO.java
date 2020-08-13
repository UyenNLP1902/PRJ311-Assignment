/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author SE140355
 */
public class UserDTO implements Serializable, Comparable<UserDTO> {

    private String username, password, role;

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public UserDTO(String role) {
        this.role = role;
    }

    public UserDTO() {
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(role);
        v.add(username);
        return v;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int compareTo(UserDTO o) {
        return this.role.compareToIgnoreCase(o.role);
    }

}
