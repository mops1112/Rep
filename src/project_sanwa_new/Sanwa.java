/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yotsathon
 */
public class Sanwa {

    public List getCurentData(String meterID, String buildingID, String roomID, Map startDateTime) throws MalformedURLException, IOException {
        List<Room> data = new ArrayList<Room>();
        DBDetail dbD = new DBDetail();
        dbD.connect();

        try {
            URL url = new URL("http://dragoservices.azurewebsites.net/api/DragoServices/MeterRecord/" + meterID + "?start=" + startDateTime.get("dateTimeServer"));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            //String line = "[{\"t\":\"2017-01-11T15:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T15:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T15:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T15:45:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T16:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T16:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T16:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T16:45:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T17:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T17:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T17:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T17:45:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T18:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T18:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T18:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T18:45:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T19:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T19:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T19:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T19:45:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T20:00:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T20:15:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T20:30:00\",\"wFl\":0.000,\"wVo\":267.720,\"wFw\":267.810,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T20:45:00\",\"wFl\":0.000,\"wVo\":267.740,\"wFw\":267.830,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T21:00:00\",\"wFl\":0.000,\"wVo\":267.740,\"wFw\":267.830,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T21:15:00\",\"wFl\":0.000,\"wVo\":267.740,\"wFw\":267.830,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T21:30:00\",\"wFl\":0.000,\"wVo\":267.740,\"wFw\":267.830,\"wRv\":0.080,\"eAE\":null},{\"t\":\"2017-01-11T21:45:00\",\"wFl\":0.000,\"wVo\":267.740,\"wFw\":267.830,\"wRv\":0.080,\"eAE\":null}]";//in.readLine();
            String line = in.readLine();
            JSONArray jsonArray = new JSONArray(line);
            data = new ArrayList<Room>();
            for (int i = jsonArray.length() - 1; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                //String dateTime = obj.getString("t");
                String dateTime = startDateTime.get("dateTimeLocal").toString();
                String date = dateTime.substring(0, 10);
                String time = dateTime.substring(11);

                //dbD.insertDetailRoom(roomID, dateTime, String.valueOf(obj.getDouble("wFl")), String.valueOf(obj.getDouble("wFw")), String.valueOf(obj.getDouble("wRv")), String.valueOf(obj.getDouble("wVo")), obj.get("eAE").toString());
                data.add(new Room(date, time, obj.getDouble("wFl"), obj.getDouble("wFw"), obj.getDouble("wRv"), obj.getDouble("wVo"), obj.get("eAE")));

            }
        } catch (Exception e) {
            data = null;
        }

        return data;

    }

    public List getDataByEnd(String startDate, String endDate, String roomID) throws MalformedURLException, IOException {
        List<Room> data;
        try {
            URL url = new URL("http://dragoservices.azurewebsites.net/api/MeterRecord/" + roomID + "?start=" + startDate);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            JSONArray jsonArray = new JSONArray(line);
            data = new ArrayList<Room>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String dateTime = obj.getString("T");
                String date = dateTime.substring(0, 10);
                String time = dateTime.substring(11);
                data.add(new Room(date, time, obj.getDouble("wFl"), obj.getDouble("wFw"), obj.getDouble("wRv"), obj.getDouble("wVo"), obj.get("eAE")));

                if (date.compareTo(endDate) == 1) {
                    break;
                }
            }
        } catch (Exception e) {
            data = null;
        }

        return data;

    }

    public void setBuilding() throws IOException, SQLException {
        URL url = new URL("http://dragoservices.azurewebsites.net/api/DragoServices/Areas/1/DragoConnexes");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = in.readLine();
        JSONArray jsonArray = new JSONArray(line);
        DBBuilding dbB = new DBBuilding();
        dbB.connect();
       
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("description");
            String connexID = obj.getString("dragoConnexID");
            if (name.isEmpty()) {
                name = connexID;
            }
            dbB.insertBuilding(connexID, name, "user");

        }
        dbB.connect.close();

    }

    public void setRooms() throws IOException, SQLException {
        DBBuilding dbB = new DBBuilding();
        dbB.connect();
        ResultSet rs = dbB.selectBuilding();
        String connexID = "";
        String building_ID = "";
        while (rs.next()) {
            building_ID = rs.getString(1);
            connexID = rs.getString(3);
            URL url = new URL("http://dragoservices.azurewebsites.net/api/DragoServices/DragoConnexes/" + connexID + "/Meters");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            //String line = "[{\"mBusID\":\"000A0637\",\"roomNo\":\"0000000109\",\"dragoConnexID\":\"00000002\",\"dragoConnexDescription\":\"\",\"description\":\"\",\"meterSetting\":\"{\\\"RoomNumber\\\":\\\"0000000101\\\",\\\"MBusID\\\":\\\"56100624\\\",\\\"MeterZigbeeMAC\\\":\\\"0004A3FFFFFF\\\",\\\"Year1\\\":\\\"2016\\\",\\\"Month1\\\":\\\"12\\\",\\\"Hour1\\\":\\\"07\\\",\\\"Minute1\\\":\\\"54\\\",\\\"Second1\\\":\\\"29\\\",\\\"Year2\\\":\\\"2016\\\",\\\"Month2\\\":\\\"12\\\",\\\"Hour2\\\":\\\"07\\\",\\\"Minute2\\\":\\\"54\\\",\\\"Second2\\\":\\\"29\\\",\\\"LogFlag\\\":\\\"000A\\\",\\\"Manufacturer\\\":\\\"FFFF\\\",\\\"Generation\\\":\\\"FF\\\",\\\"Medium\\\":\\\"02\\\",\\\"AlarmNumber\\\":\\\"001\\\",\\\"SerialNumber\\\":\\\"0000000000\\\",\\\"InformationString\\\":\\\"0000000000\\\",\\\"AvgUsagePerDay\\\":\\\"0000000000\\\",\\\"ConsecutiveRecordsBeforeAlert\\\":\\\"00\\\",\\\"HighTreshold\\\":\\\"0000000000\\\",\\\"LowTreshold\\\":\\\"0000000000\\\"}\",\"createdDateTime\":\"2016-12-26T07:54:29.583\",\"createdBy\":3,\"createdByUserName\":\"Tester\",\"updatedDateTime\":\"2016-12-26T07:54:29.583\",\"updatedBy\":3,\"updatedByUserName\":\"Tester\"},{\"mBusID\":\"53100126\",\"roomNo\":\"0000000101\",\"dragoConnexID\":\"00000002\",\"dragoConnexDescription\":\"\",\"description\":\"\",\"meterSetting\":\"{\\\"RoomNumber\\\":\\\"0000000101\\\",\\\"MBusID\\\":\\\"53100126\\\",\\\"MeterZigbeeMAC\\\":\\\"0004A3FFFFFF\\\",\\\"Year1\\\":\\\"2016\\\",\\\"Month1\\\":\\\"12\\\",\\\"Hour1\\\":\\\"09\\\",\\\"Minute1\\\":\\\"20\\\",\\\"Second1\\\":\\\"19\\\",\\\"Year2\\\":\\\"2016\\\",\\\"Month2\\\":\\\"12\\\",\\\"Hour2\\\":\\\"09\\\",\\\"Minute2\\\":\\\"20\\\",\\\"Second2\\\":\\\"19\\\",\\\"LogFlag\\\":\\\"000A\\\",\\\"Manufacturer\\\":\\\"FFFF\\\",\\\"Generation\\\":\\\"FF\\\",\\\"Medium\\\":\\\"07\\\",\\\"AlarmNumber\\\":\\\"001\\\",\\\"SerialNumber\\\":\\\"0000000000\\\",\\\"InformationString\\\":\\\"0000000000\\\",\\\"AvgUsagePerDay\\\":\\\"0000000000\\\",\\\"ConsecutiveRecordsBeforeAlert\\\":\\\"00\\\",\\\"HighTreshold\\\":\\\"0000000000\\\",\\\"LowTreshold\\\":\\\"0000000000\\\"}\",\"createdDateTime\":\"2016-12-23T09:20:19.587\",\"createdBy\":3,\"createdByUserName\":\"Tester\",\"updatedDateTime\":\"2016-12-23T09:20:19.587\",\"updatedBy\":3,\"updatedByUserName\":\"Tester\"},{\"mBusID\":\"53100127\",\"roomNo\":\"0000000102\",\"dragoConnexID\":\"00000002\",\"dragoConnexDescription\":\"\",\"description\":\"\",\"meterSetting\":\"{\\\"RoomNumber\\\":\\\"0000000102\\\",\\\"MBusID\\\":\\\"53100127\\\",\\\"MeterZigbeeMAC\\\":\\\"0004A3FFFFFF\\\",\\\"Year1\\\":\\\"2016\\\",\\\"Month1\\\":\\\"12\\\",\\\"Hour1\\\":\\\"09\\\",\\\"Minute1\\\":\\\"21\\\",\\\"Second1\\\":\\\"05\\\",\\\"Year2\\\":\\\"2016\\\",\\\"Month2\\\":\\\"12\\\",\\\"Hour2\\\":\\\"09\\\",\\\"Minute2\\\":\\\"21\\\",\\\"Second2\\\":\\\"05\\\",\\\"LogFlag\\\":\\\"000A\\\",\\\"Manufacturer\\\":\\\"FFFF\\\",\\\"Generation\\\":\\\"FF\\\",\\\"Medium\\\":\\\"07\\\",\\\"AlarmNumber\\\":\\\"001\\\",\\\"SerialNumber\\\":\\\"0000000000\\\",\\\"InformationString\\\":\\\"0000000000\\\",\\\"AvgUsagePerDay\\\":\\\"0000000000\\\",\\\"ConsecutiveRecordsBeforeAlert\\\":\\\"00\\\",\\\"HighTreshold\\\":\\\"0000000000\\\",\\\"LowTreshold\\\":\\\"0000000000\\\"}\",\"createdDateTime\":\"2016-12-23T09:21:05.49\",\"createdBy\":3,\"createdByUserName\":\"Tester\",\"updatedDateTime\":\"2016-12-23T09:21:05.49\",\"updatedBy\":3,\"updatedByUserName\":\"Tester\"},{\"mBusID\":\"53100128\",\"roomNo\":\"0000000103\",\"dragoConnexID\":\"00000002\",\"dragoConnexDescription\":\"\",\"description\":\"\",\"meterSetting\":\"{\\\"RoomNumber\\\":\\\"0000000103\\\",\\\"MBusID\\\":\\\"53100128\\\",\\\"MeterZigbeeMAC\\\":\\\"0004A3FFFFFF\\\",\\\"Year1\\\":\\\"2016\\\",\\\"Month1\\\":\\\"12\\\",\\\"Hour1\\\":\\\"09\\\",\\\"Minute1\\\":\\\"21\\\",\\\"Second1\\\":\\\"18\\\",\\\"Year2\\\":\\\"2016\\\",\\\"Month2\\\":\\\"12\\\",\\\"Hour2\\\":\\\"09\\\",\\\"Minute2\\\":\\\"21\\\",\\\"Second2\\\":\\\"18\\\",\\\"LogFlag\\\":\\\"000A\\\",\\\"Manufacturer\\\":\\\"FFFF\\\",\\\"Generation\\\":\\\"FF\\\",\\\"Medium\\\":\\\"07\\\",\\\"AlarmNumber\\\":\\\"001\\\",\\\"SerialNumber\\\":\\\"0000000000\\\",\\\"InformationString\\\":\\\"0000000000\\\",\\\"AvgUsagePerDay\\\":\\\"0000000000\\\",\\\"ConsecutiveRecordsBeforeAlert\\\":\\\"00\\\",\\\"HighTreshold\\\":\\\"0000000000\\\",\\\"LowTreshold\\\":\\\"0000000000\\\"}\",\"createdDateTime\":\"2016-12-23T09:21:18.593\",\"createdBy\":3,\"createdByUserName\":\"Tester\",\"updatedDateTime\":\"2016-12-23T09:21:18.593\",\"updatedBy\":3,\"updatedByUserName\":\"Tester\"},{\"mBusID\":\"56100624\",\"roomNo\":\"0000000101\",\"dragoConnexID\":\"00000002\",\"dragoConnexDescription\":\"\",\"description\":\"\",\"meterSetting\":\"{\\\"RoomNumber\\\":\\\"0000000101\\\",\\\"MBusID\\\":\\\"56100624\\\",\\\"MeterZigbeeMAC\\\":\\\"0004A3FFFFFF\\\",\\\"Year1\\\":\\\"2016\\\",\\\"Month1\\\":\\\"12\\\",\\\"Hour1\\\":\\\"07\\\",\\\"Minute1\\\":\\\"54\\\",\\\"Second1\\\":\\\"29\\\",\\\"Year2\\\":\\\"2016\\\",\\\"Month2\\\":\\\"12\\\",\\\"Hour2\\\":\\\"07\\\",\\\"Minute2\\\":\\\"54\\\",\\\"Second2\\\":\\\"29\\\",\\\"LogFlag\\\":\\\"000A\\\",\\\"Manufacturer\\\":\\\"FFFF\\\",\\\"Generation\\\":\\\"FF\\\",\\\"Medium\\\":\\\"07\\\",\\\"AlarmNumber\\\":\\\"001\\\",\\\"SerialNumber\\\":\\\"0000000000\\\",\\\"InformationString\\\":\\\"0000000000\\\",\\\"AvgUsagePerDay\\\":\\\"0000000000\\\",\\\"ConsecutiveRecordsBeforeAlert\\\":\\\"00\\\",\\\"HighTreshold\\\":\\\"0000000000\\\",\\\"LowTreshold\\\":\\\"0000000000\\\"}\",\"createdDateTime\":\"2016-12-26T07:54:29.583\",\"createdBy\":3,\"createdByUserName\":\"Tester\",\"updatedDateTime\":\"2016-12-26T07:54:29.583\",\"updatedBy\":3,\"updatedByUserName\":\"Tester\"}]";//in.readLine();
            String line = in.readLine();
            JSONArray jsonArray = new JSONArray(line);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String roomNo = obj.getString("roomNo");
                String mBusID = obj.getString("mBusID");
                String e_meter = mBusID;
                String w_meter = mBusID;
                //db.insertRoom(building_ID, roomNo, e_meter, w_meter, "user");

            }
        }
        dbB.connect.close();
    }

    public Map getCurentDateTime() {
        Map<String, String> dateTimeStart = new HashMap<String, String>();
        DateFormat dateFormat = new SimpleDateFormat("mm", Locale.ENGLISH);
        String minute = dateFormat.format(new Date());
        //minute = Integer.toString((Integer.parseInt(minute) / 15) * 15);
        minute = String.format("%02d", (Integer.parseInt(minute) / 15) * 15);
        //minute = String.format("%02d", (Integer.parseInt(minute) / 15) * 15);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:" + minute + ":00", Locale.ENGLISH);
        dateTimeStart.put("dateTimeLocal", dateFormat.format(new Date()));
        //dateTimeStart.put("dateTimeLocal", "2016-11-01T14:45:00") ;
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        dateTimeStart.put("dateTimeServer", dateFormat.format(new Date()));
        //dateTimeStart.put("dateTimeServer", "2016-11-01T21:45:00") ;
        return dateTimeStart;
    }

    
}
