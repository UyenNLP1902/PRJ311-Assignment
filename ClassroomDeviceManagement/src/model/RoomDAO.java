/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conn.MyConnection;
import dtos.RoomDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SE140355
 */
public class RoomDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSta;
    private ResultSet rs;

    public RoomDAO() {
    }

    public RoomDAO(Connection conn, PreparedStatement preSta, ResultSet rs) {
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

    public List<RoomDTO> getAllRoom() throws Exception {
        String roomID = null;
        String roomDes = null;
        RoomDTO dto = null;
        List<RoomDTO> result = null;

        try {
            String sql = "Select RoomID, Room_Description From tblRoom";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getString("RoomID");
                roomDes = rs.getString("Room_Description");
                dto = new RoomDTO(roomID, roomDes);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public List<RoomDTO> getRoomAdmin(String username) throws Exception {
        String roomID = null;
        String roomDes = null;
        RoomDTO dto = null;
        List<RoomDTO> result = null;

        try {
            String sql = "Select r.RoomID, Room_Description "
                    + "From tblRoom r "
                    + "Join tblOccupied o "
                    + "On r.RoomID = o.RoomID "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getString("RoomID");
                roomDes = rs.getString("Room_Description");
                dto = new RoomDTO(roomID, roomDes);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public List<RoomDTO> getRoomStudentAndTeacher(String username) throws Exception {
        String roomID = null, roomDes = null;
        RoomDTO dto = null;
        List<RoomDTO> result = null;

        try {
            String sql = "Select RoomID "
                    + "From tblOccupied "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getString("RoomID");
                dto = new RoomDTO(roomID, roomDes);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<String> getRoomList(List<RoomDTO> list) throws Exception {
        List<String> roomList = new ArrayList<>();
        
        for (RoomDTO roomDTO : list) {
            roomList.add(roomDTO.getRoomID());
        }
        
        return roomList;
    }

    public String getRoomDesByID(String id) throws Exception {
        String roomDes = "";

        try {
            String sql = "SELECT Room_Description "
                    + "FROM tblRoom "
                    + "WHERE RoomID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, id);
            rs = preSta.executeQuery();

            if (rs.next()) {
                roomDes = rs.getString("Room_Description");
            }
        } catch (Exception e) {
        }
        return roomDes;
    }

    public boolean addRoom(String roomId, String roomDes) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblRoom(RoomID, Room_Description) "
                    + "Values(?, ?)";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, roomId);
            preSta.setString(2, roomDes);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateRoom(String roomId, String roomDes) throws Exception {
        boolean check = false;

        try {
            String sql = "Update tblRoom "
                    + "Set Room_Description = ? "
                    + "Where RoomID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, roomDes);
            preSta.setString(2, roomId);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }
    

}
