package com.example.myapplication;

import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class otherThread extends Thread {


    @Override
    public void run() {
        try{
            final String catchData = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-7266AF3A-FF88-4BCF-AE0D-0FA93EE572F9&format=JSON&locationName=%E8%87%BA%E5%8C%97%E5%B8%82&elementName=MinT";
            URL url = new URL(catchData);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = in.readLine();
            StringBuffer json = new StringBuffer();
            while (line != null) {
                json.append(line);
                line = in.readLine();
            }

            JSONObject jsonObject = new JSONObject();
            JSONObject result = jsonObject.getJSONObject("result");
            String resource_id = result.getString("resource_id");
            JSONArray array = result.getJSONArray("fields");
            for(int i=0;i<array.length();i++){
                JSONObject jsonObject1=array.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String type = jsonObject1.getString("type");
            }

            JSONObject records = jsonObject.getJSONObject("records");
            String datasetDescription = records.getString("datasetDescription");
            JSONObject location = records.getJSONObject("location");
            String locationName = location.getString("locationName");
            JSONObject weatherElement = location.getJSONObject("weatherElement");
            String elementName = weatherElement.getString("elementName");
            JSONArray array1 = weatherElement.getJSONArray("time");
            for(int i=0;i<array1.length();i++){
                JSONObject jsonObject2=array1.getJSONObject(i);
                String startTime = jsonObject2.getString("startTime");
                String endTime = jsonObject2.getString("endTime");
                JSONObject parameter = jsonObject2.getJSONObject("parameter");
                String parameterName = parameter.getString("parameterName");
                String parameterUnit = parameter.getString("parameterUnit");

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("startTime",startTime);
                hashMap.put("endTime",endTime);
                hashMap.put("parameterName",parameterName);
                hashMap.put("parameterUnit",parameterUnit);

            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
