/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conn.MyConnection;
import dtos.UserDTO;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SE140355
 */
public class UserDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSta;
    private ResultSet rs;

    public UserDAO() {
    }

    public UserDAO(Connection conn, PreparedStatement preSta, ResultSet rs) {
        this.conn = conn;
        this.preSta = preSta;
        this.rs = rs;
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preSta != null) {
            preSta.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public UserDTO login(String username, String password, String role) throws Exception {
        UserDTO dto = null;

        try {
            String sql = "Select Username, Role From tblLogin "
                    + "Where Username = ? And Password = ? And Role = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            preSta.setString(2, password);
            preSta.setString(3, role);

            rs = preSta.executeQuery();
            while (rs.next()) {
                dto = new UserDTO(username, role);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public List<UserDTO> getAllUser() throws Exception {
        List<UserDTO> result = null;
        UserDTO dto = null;

        try {
            String sql = "Select Username, Role "
                    + "From tblLogin";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String role = rs.getString("Role");
                String username = rs.getString("Username");

                dto = new UserDTO(username, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<UserDTO> getUserByRole(String role) throws Exception {
        List<UserDTO> result = null;
        UserDTO dto = null;

        try {
            String sql = "Select Username "
                    + "From tblLogin "
                    + "Where Role = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, role);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("Username");

                dto = new UserDTO(username, role);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }

        return result;
    }

    public List<UserDTO> getUserByRoom(String room) throws Exception {
        List<UserDTO> result = null;
        UserDTO dto = null;

        try {
            String sql = "Select l.Username, Role "
                    + "From tblLogin l "
                    + "Join tblOccupied o "
                    + "On l.Username = o.Username "
                    + "Where RoomID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, room);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("Username");
                String role = rs.getString("Role");

                dto = new UserDTO(username, role);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }

        return result;
    }

    public UserDTO searchByUsername(String username) throws Exception {
        UserDTO dto = null;

        try {
            String sql = "Select Role "
                    + "From tblLogin "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            rs = preSta.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");

                dto = new UserDTO(role);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public String getRoomWorkInByUsername(String username) throws Exception {
        String listRoom = "";
        List<String> list = new ArrayList<>();

        try {
            String sql = "Select RoomID "
                    + "From tblOccupied "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            rs = preSta.executeQuery();

            while (rs.next()) {
                String room = rs.getString("RoomID");

                list.add(room);
            }
        } finally {
            closeConnection();
        }

        for (String room : list) {
            listRoom += room + ", ";
        }

        return listRoom;
    }

    public boolean addUser(String username, String role, String password)
            throws Exception {
        boolean check = false;

        try {
            String sql = "Insert Into tblLogin(Username, Role, Password) "
                    + "Values(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            preSta.setString(2, role);
            preSta.setString(3, password);

            check = preSta.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateUser(String username, String role, String password)
            throws Exception {
        boolean check = false;

        try {
            String sql = "Update tblLogin "
                    + "Set Password = ?, Role = ? "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, password);
            preSta.setString(2, role);
            preSta.setString(3, username);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    private boolean deleteFromTblOccupied(String username) throws Exception {
        boolean check = false;

        try {
            String sql = "Delete From tblOccupied "
                    + "Where Username  = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);

            check = preSta.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    private boolean deleteFromTblLogin(String username) throws Exception {
        boolean check = false;

        try {
            String sql = "Delete From tblLogin "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deleteUser(String username) throws Exception {
        boolean check = false;
        deleteFromTblOccupied(username);
        if (deleteFromTblLogin(username)) {
            check = true;
        }

        return check;
    }

    public boolean addJob(String username, String room, String work) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert Into tblOccupied(Username, RoomID, Job) "
                    + "Values(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            preSta.setString(2, room);
            preSta.setString(3, work);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deleteJob(String username, String room) throws Exception {
        boolean check = false;

        try {
            String sql = "Delete From tblOccupied "
                    + "Where Username = ? And RoomID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            preSta.setString(2, room);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }
}
