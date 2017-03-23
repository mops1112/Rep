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
public class DataAlert {

    String siteName;
    String areaName;
    String dragoConnexName;
    String roomNumber;
    String mBusID;
    String limit;
    String wFw;
    String date;
    String time;

    public DataAlert(String siteName, String areaName, String dragoConnexName, String roomNumber, String mBusID, String limit, String wFw, String date, String time) {
        this.siteName = siteName;
        this.areaName = areaName;
        this.dragoConnexName = dragoConnexName;
        this.roomNumber = roomNumber;
        this.mBusID = mBusID;
        this.limit = limit;
        this.wFw = wFw;
        this.date = date;
        this.time = time;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDragoConnexName() {
        return dragoConnexName;
    }

    public void setDragoConnexName(String dragoConnexName) {
        this.dragoConnexName = dragoConnexName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getmBusID() {
        return mBusID;
    }

    public void setmBusID(String mBusID) {
        this.mBusID = mBusID;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getwFw() {
        return wFw;
    }

    public void setwFw(String wFw) {
        this.wFw = wFw;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    

    
}
