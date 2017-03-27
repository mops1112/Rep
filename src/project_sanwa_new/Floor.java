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
    String floorNumber;

    public Floor(String floorID, String floorNumber) {
        this.floorID = floorID;
        this.floorNumber = floorNumber;
    }

    public String getFloorID() {
        return floorID;
    }

    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

   


    @Override
    public String toString() {
        return  "floor : "+floorNumber ;
    }


    
    
    
}
