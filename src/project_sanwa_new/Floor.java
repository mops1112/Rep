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
public class Floor {
    String floorID;
    String floorName;

    public Floor(String floorID, String floorName) {
        this.floorID = floorID;
        this.floorName = floorName;
    }

    public String getFloorID() {
        return floorID;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Override
    public String toString() {
        return "floor " + floorName;
    }

    
    
    
}
