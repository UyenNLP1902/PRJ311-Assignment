/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author SE140355
 */
public class FeedbackDTO implements Serializable, Comparable<FeedbackDTO> {

    private String id, username, roomId, equipId, content, reply;
    private String status;
    private Timestamp date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String id, String username, String roomId, String equipId, String content, String reply, String status, Timestamp date) {
        this.id = id;
        this.username = username;
        this.roomId = roomId;
        this.equipId = equipId;
        this.content = content;
        this.reply = reply;
        this.status = status;
        this.date = date;
    }

    public FeedbackDTO(String id, String roomId, String equipId, String status, Timestamp date) {
        this.id = id;
        this.roomId = roomId;
        this.equipId = equipId;
        this.status = status;
        this.date = date;
    }

    public FeedbackDTO(String id, String username, String roomId, String equipId, String status, Timestamp date) {
        this.id = id;
        this.username = username;
        this.roomId = roomId;
        this.equipId = equipId;
        this.status = status;
        this.date = date;
    }

    public Vector toVectorAdminandTeacher() {
        Vector v = new Vector();
        v.add(id);
        v.add(username);
        v.add(roomId);
        v.add(equipId);
        v.add(date);
        v.add(status);
        return v;
    }

    public Vector toVectorStudent() {
        Vector v = new Vector();
        v.add(id);
        v.add(roomId);
        v.add(equipId);
        v.add(date);
        v.add(status);
        return v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public int compareTo(FeedbackDTO o) {
        return -this.status.compareTo(o.status);
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" + "id=" + id + ", username=" + username + ", roomId=" + roomId + ", equipId=" + equipId + ", content=" + content + ", reply=" + reply + ", status=" + status + ", date=" + date + '}';
    }

}
