/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yotsathon
 */
public class ListAlert {

    public static List<DataAlert> listAlert;

    public ListAlert() {
        listAlert = new ArrayList<DataAlert>();
    }

    public void setListAlert(String siteName, String areaName, String dragoConnexName, String roomNumber, String mBusID, String limit, String wFl, String date, String time) {
        this.listAlert.add(new DataAlert(siteName,  areaName,  dragoConnexName,  roomNumber,  mBusID,  limit,  wFl, date, time));
        //this.listAlert = listAlert;
    }

    public static List<DataAlert> getListAlert() {
        return listAlert;
    }


    
}
