/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.UUID;
import conn.MyConnection;
import dtos.FeedbackDTO;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author SE140355
 */
public class FeedbackDAO implements Runnable {// extends Thread{

    private JLabel label;
    private Connection conn;
    private PreparedStatement preSta;
    private ResultSet rs;

    private static String generateString() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public FeedbackDAO() {
    }

    public FeedbackDAO(JLabel label) {
        this.label = label;
    }

    public FeedbackDAO(Connection conn, PreparedStatement preSta, ResultSet rs) {
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

    public List<FeedbackDTO> getAllFeedbackByUsername(String username) throws Exception {
        String roomId, equipId;
        Timestamp date;
        String status;

        FeedbackDTO dto = null;
        List<FeedbackDTO> result = null;

        try {
            String sql = "Select ID, RoomID, EquipmentID, Status, Date "
                    + "From tblFeedback "
                    + "Where Username = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, username);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("ID");
                roomId = rs.getString("RoomID");
                equipId = rs.getString("EquipmentID");
                date = rs.getTimestamp("Date");
                status = rs.getString("Status");

                dto = new FeedbackDTO(id, roomId, equipId, status, date);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }

        return result;
    }

    public List<FeedbackDTO> getAllFeedbackByRoom(String roomId) throws Exception {
        String username, equipId;
        Timestamp date;
        String status;

        FeedbackDTO dto = null;
        List<FeedbackDTO> result = null;

        try {
            String sql = "Select ID, l.Username, EquipmentID, Status, Date "
                    + "From tblFeedback f "
                    + "Join tblLogin l "
                    + "On f.Username = l.Username "
                    + "Where RoomID = ? And l.Role != ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, roomId);
            preSta.setString(2, "Teacher");
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("ID");
                username = rs.getString("Username");
                equipId = rs.getString("EquipmentID");
                date = rs.getTimestamp("Date");
                status = rs.getString("Status");

                dto = new FeedbackDTO(id, username, roomId, equipId, status, date);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }

        return result;
    }

    public List<FeedbackDTO> getAllFeedbackAdmin() throws Exception {
        List<FeedbackDTO> result = null;

        String username, roomId, equipId;
        Timestamp date;
        String status;

        FeedbackDTO dto = null;

        try {
            String sql = "Select ID, Username, RoomID, EquipmentID, Date, Status "
                    + "From tblFeedback";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            rs = preSta.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("ID");
                username = rs.getString("Username");
                roomId = rs.getString("RoomID");
                equipId = rs.getString("EquipmentID");
                date = rs.getTimestamp("Date");
                status = rs.getString("Status");

                dto = new FeedbackDTO(id, username, roomId, equipId, status, date);

                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean insert(String username, String roomId, String equipId,
            String content, Timestamp date)
            throws Exception {
        boolean check = false;

        try {
            String sql = "Insert Into tblFeedback(ID, Username, RoomID, EquipmentID, Status, Date, ContentFeedback, Reply) "
                    + "Values(?, ?, ?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, generateString());
            preSta.setString(2, username);
            preSta.setString(3, roomId);
            preSta.setString(4, equipId);
            preSta.setString(5, "Waiting");
            preSta.setTimestamp(6, date);
            preSta.setString(7, content);
            preSta.setString(8, " ");

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public FeedbackDTO searchByPrimaryKeys(String id) throws Exception {
        FeedbackDTO dto = null;

        try {

            String sql = "Select Username, RoomID, EquipmentID, Status, ContentFeedback, Reply, Date "
                    + "From tblFeedback "
                    + "Where ID = ?";

            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, id);

            rs = preSta.executeQuery();

            if (rs.next()) {
                String user = rs.getString("Username");
                String room = rs.getString("RoomID");
                String equipId = rs.getString("EquipmentID");
                String status = rs.getString("Status");
                String content = rs.getString("ContentFeedback");
                String reply = rs.getString("Reply");
                Timestamp date = rs.getTimestamp("Date");

                dto = new FeedbackDTO(id, user, room, equipId, content, reply, status, date);

            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public boolean reply(String id, String reply, String status) throws Exception {
        boolean check = false;

        try {
            String sql = "Update tblFeedback "
                    + "Set Reply = ?, Status = ? "
                    + "Where ID = ? ";

            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, reply);
            preSta.setString(2, status);
            preSta.setString(3, id);

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean autoReply() throws Exception {
        boolean check = false;

        try {
            String sql = "Update tblFeedback "
                    + "Set Reply = ?, Status = ? "
                    + "Where Date < DATEADD(day, -2, GETDATE()) And Status = 'Waiting'";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, "Request expired!");
            preSta.setString(2, "Declined");

            check = preSta.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    private int countIrresponsed() throws Exception {
        int count = 0;
        try {
            String sql = "Select COUNT(ID) as Count "
                    + "From tblFeedback "
                    + "Where Status = ?";
            conn = MyConnection.getMyConnection();
            preSta = conn.prepareStatement(sql);
            preSta.setString(1, "Waiting");
            rs = preSta.executeQuery();

            if (rs.next()) {
                count = rs.getInt("Count");
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public void setCountText() throws Exception {
        label.setText("You have " + countIrresponsed() + " unresponsed feedback.");
    }

    
    @Override
    public void run() {
        try {
            autoReply();
            //setCountText();
        } catch (Exception ex) {

            System.out.println("Error!");

        }

    }

}
