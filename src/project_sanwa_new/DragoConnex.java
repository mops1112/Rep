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
public class DragoConnex {
    String dragoConnexID;
    String description;

    public DragoConnex(String dragoConnexID, String description) {
        this.dragoConnexID = dragoConnexID;
        this.description = description;
    }

    public String getDragoConnexID() {
        return dragoConnexID;
    }

    public void setDragoConnexID(String dragoConnexID) {
        this.dragoConnexID = dragoConnexID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  description ;
    }
    
}
