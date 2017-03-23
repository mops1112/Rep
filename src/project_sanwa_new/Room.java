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
public class Room {

    private String date;
    private String time;
    private Double wFl;
    private Double wFw;
    private Double wRv;
    private Double wVo;
    private Object eAE;

    public Room(String date, String time, Double wFl, Double wFw, Double wRv, Double wVo, Object eAE) {
        this.date = date;
        this.time = time;
        this.wFl = wFl;
        this.wFw = wFw;
        this.wRv = wRv;
        this.wVo = wVo;
        this.eAE = eAE;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Double getwFl() {
        return wFl;
    }

    public Double getwFw() {
        return wFw;
    }

    public Double getwRv() {
        return wRv;
    }

    public Double getwVo() {
        return wVo;
    }



    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setwFl(Double wFl) {
        this.wFl = wFl;
    }

    public void setwFw(Double wFw) {
        this.wFw = wFw;
    }

    public void setwRv(Double wRv) {
        this.wRv = wRv;
    }

    public void setwVo(Double wVo) {
        this.wVo = wVo;
    }

    public Object geteAE() {
        return eAE;
    }

    public void seteAE(Object eAE) {
        this.eAE = eAE;
    }



}
