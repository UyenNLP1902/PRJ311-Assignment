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
public class EquipmentDTO implements Serializable {

    private String equipmentID, equipmentName, roomID;
    private boolean equipmentStatus;

    public EquipmentDTO() {
    }

    public EquipmentDTO(String equipmentID, String equipmentName) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
    }

    public EquipmentDTO(String equipmentID, String equipmentName, boolean equipmentStatus) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentStatus = equipmentStatus;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(equipmentID);
        v.add(equipmentName);
        v.add(equipmentStatus);
        return v;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public boolean isEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(boolean equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

}
