
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import project_sanwa_new.DBArea;
import project_sanwa_new.DBDragoConnex;
import project_sanwa_new.DBRoom;
import project_sanwa_new.DBSites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yotsathon
 */
public class temp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        DBSites dbS = new DBSites();
        DBArea dbA = new DBArea();
        DBDragoConnex dbDC = new DBDragoConnex();
        DBRoom dbR = new DBRoom();

        dbS.connect();
        dbA.connect();
        dbDC.connect();
        dbR.connect();
        URL url;
        BufferedReader in;
        JSONArray jsonArray;
        JSONObject obj = null;
        
        url = new URL("http://dragoservices.azurewebsites.net/api/DragoServices/Owners");
        in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = in.readLine();
       
        //line = "["+line+"]";
        if (!"[]".equals(line)) {
            obj = new JSONObject(line);
//            for (int i = 0; i < obj.getJSONArray("sites").length(); i++) {
//                dbS.insertSite((obj.getJSONArray("sites")).getJSONObject(i).get("siteID").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("code").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("description").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("createdDateTime").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("updatedDateTime").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("isActive").toString(), (obj.getJSONArray("sites")).getJSONObject(i).get("address").toString());
//            }
//            for (int i = 0; i < obj.getJSONArray("areas").length(); i++) {
//                dbA.insertArea((obj.getJSONArray("areas")).getJSONObject(i).get("areaID").toString(), (obj.getJSONArray("areas")).getJSONObject(i).get("siteID").toString(), (obj.getJSONArray("areas")).getJSONObject(i).get("code").toString(), (obj.getJSONArray("areas")).getJSONObject(i).get("description").toString());
//            }
//            for (int i = 0; i < obj.getJSONArray("dragoConnexes").length(); i++) {
//                dbDC.insertDragoConnex((obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("dragoConnexID").toString(), (obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("areaID").toString(), (obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("description").toString(), (obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("createdDateTime").toString(), (obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("updatedDateTime").toString(), (obj.getJSONArray("dragoConnexes")).getJSONObject(i).get("isActive").toString());
//            }
            for (int i = 0; i < obj.getJSONArray("meters").length(); i++) {
                JSONObject roomNumber = new JSONObject((obj.getJSONArray("meters")).getJSONObject(i).get("meterSetting").toString());
               // dbR.insertRoom((obj.getJSONArray("meters")).getJSONObject(i).get("mBusID").toString(), (obj.getJSONArray("meters")).getJSONObject(i).get("dragoConnexID").toString(), roomNumber.get("RoomNumber").toString());

            }


        }
    }

}
