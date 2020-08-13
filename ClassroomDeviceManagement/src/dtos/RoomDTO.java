/*
 * 3To change this license header, choose License Headers in Project Properties.
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
public class RoomDTO implements Serializable {

    private String roomID, roomDescription;

    public RoomDTO() {
    }

    public RoomDTO(String roomID) {
        this.roomID = roomID;
    }

    public RoomDTO(String roomID, String roomDescription) {
        this.roomID = roomID;
        this.roomDescription = roomDescription;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(roomID);
        return v;
    }
    
    public Vector toVectorEmployer() {
        Vector v = new Vector();
        v.add(roomID);
        v.add(roomDescription);
        
        return v;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

}
