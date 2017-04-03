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
public class CountToAlert {

    String roomNumber;
    int over;
    int leak;
    int disconnect;
    int perDay;
    int time;

    public CountToAlert(String roomNumber, int over, int leak, int disconnect, int perDay, int time) {
        this.roomNumber = roomNumber;
        this.over = over;
        this.leak = leak;
        this.disconnect = disconnect;
        this.perDay = perDay;
        this.time = time;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getOver() {
        return over;
    }

    public void setOver() {
        this.over = this.over + 1;
    }

    public int getLeak() {
        return leak;
    }

    public void setLeak() {
        this.leak = this.leak + 1;
    }

    public int getDisconnect() {
        return disconnect;
    }

    public void setDisconnect() {
        this.disconnect = this.disconnect + 1;
    }

    public int getPerDay() {
        return perDay;
    }

    public void setPerDay() {
        this.perDay = this.perDay + 1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = this.time + 1;
    }

}
