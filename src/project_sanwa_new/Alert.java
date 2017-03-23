/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;



/**
 *
 * @author yotsathon
 */
public class Alert {
    String roomID;
    String message;
    String roomNumber;
    

    public Alert() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Alert(String roomID, String message, String roomNumber) {
        this.roomID = roomID;
        this.message = message;
        this.roomNumber = roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Alert{" + "message=" + message + ", roomNumber=" + roomNumber + '}';
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
