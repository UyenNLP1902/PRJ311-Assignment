/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conn.MyConnection;
import dtos.EquipmentDTO;
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
public class EquipmentDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSta;
    private ResultSet rs;

    public EquipmentDAO() {
    }

    public EquipmentDAO(Connection conn, PreparedStatement preSta, ResultSet rs) {
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

    public List<EquipmentDTO> getAllEquipAdmin() throws Exception {
        String equipId = null, equipName = null;
        boolean equipStatus = false;
        EquipmentDTO dto = null;
        List<EquipmentDTO> result = null;

        try {
            String sql = "SELECT EquipmentID, EquipmentName, EquipmentStatus "
                    + "FROM tblEquipment";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                equipId = rs.getString("EquipmentID");
                equipName = rs.getString("EquipmentName");
                equipStatus = rs.getBoolean("EquipmentStatus");
                dto = new EquipmentDTO(equipId, equipName, equipStatus);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<EquipmentDTO> findByRoomID(String roomID) throws Exception {

        List<EquipmentDTO> result = null;
        EquipmentDTO dto = null;
        String equipId, equipName;
        boolean equipStatus;

        try {
            String sql = "SELECT EquipmentID, EquipmentName, EquipmentStatus "
                    + "FROM tblEquipment "
                    + "WHERE RoomID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, roomID);
            rs = preSta.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                equipId = rs.getString("EquipmentID");
                equipName = rs.getString("EquipmentName");
                equipStatus = rs.getBoolean("EquipmentStatus");
                dto = new EquipmentDTO(equipId, equipName);
                dto.setEquipmentStatus(equipStatus);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public EquipmentDTO findByPrimaryKeyStudent(String id) throws Exception {
        EquipmentDTO dto = null;

        try {
            String sql = "Select EquipmentID, EquipmentName, RoomID "
                    + "From tblEquipment Where EquipmentID = ?";

            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, id);
            rs = preSta.executeQuery();

            if (rs.next()) {
                String equipId = rs.getString("EquipmentID");
                String equipName = rs.getString("EquipmentName");
                String roomID = rs.getString("RoomID");
                dto = new EquipmentDTO(equipId, equipName);
                dto.setRoomID(roomID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public EquipmentDTO findByPrimaryKeyAdminAndTeacher(String id) throws Exception {
        EquipmentDTO dto = null;

        try {
            String sql = "SELECT EquipmentName, EquipmentStatus, RoomID "
                    + "FROM tblEquipment "
                    + "WHERE EquipmentID = ?";

            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, id);
            rs = preSta.executeQuery();

            if (rs.next()) {
                String equipName = rs.getString("EquipmentName");
                boolean equipStatus = rs.getBoolean("EquipmentStatus");
                String roomID = rs.getString("RoomID");

                dto = new EquipmentDTO(id, equipName);
                dto.setRoomID(roomID);
                dto.setEquipmentStatus(equipStatus);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean addEquipment(String equipId, String equipName,
            boolean equipStatus, String roomId) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert Into tblEquipment(EquipmentID, EquipmentName, EquipmentStatus, RoomID) "
                    + "Values(?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, equipId);
            preSta.setString(2, equipName);
            preSta.setBoolean(3, equipStatus);
            preSta.setString(4, roomId);

            check = preSta.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateEquip(String equipId, String equipName,
            boolean equipStatus, String roomId) throws Exception {
        boolean check = false;

        try {
            String sql = "Update tblEquipment "
                    + "Set EquipmentName = ?, EquipmentStatus = ?, RoomID = ? "
                    + "Where EquipmentID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, equipName);
            preSta.setBoolean(2, equipStatus);
            preSta.setString(3, roomId);
            preSta.setString(4, equipId);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deleleEquip(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete From tblEquipment "
                    + "Where EquipmentID = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, id);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

}
