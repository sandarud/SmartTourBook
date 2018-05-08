package com.example.sandarudaminda.smarttourbook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sandaru Daminda on 3/28/2018.
 */

public class DataParser
{

   private HashMap<String,String>getPlace (JSONObject googlePlaceJson)
   {
       HashMap<String,String>googlePlaceMap=new HashMap<>();
       String placeName="-NA-";
       String vicinity="-NA-";
       String latitude="";
       String longitude="";
       String reference="";
       try
       {
             if (!googlePlaceJson.isNull("name"))
                {

               placeName=googlePlaceJson.getString("name");
                }

                if (!googlePlaceJson.isNull("vicinity"))
                {
                    vicinity=googlePlaceJson.getString("visinity");
                }
                latitude=googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
                longitude=googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");

                reference=googlePlaceJson.getString("reference");
                googlePlaceMap.put("Place_name",placeName);
                 googlePlaceMap.put("vicinity",vicinity);
                 googlePlaceMap.put("lat",latitude);
                 googlePlaceMap.put("lag",longitude);
                 googlePlaceMap.put("reference",reference);

       }
       catch (JSONException e)
       {
           e.printStackTrace();
       }
return googlePlaceMap;
   }

   private List<HashMap<String,String>> getPlaces(JSONArray jsonArray)
   {
       int count=jsonArray.length();
       List<HashMap<String,String>> placesList=new ArrayList<>();
       HashMap<String,String> placeMap=null;

       for (int i=0;i<count;i++)
       {
           try {
               placeMap=getPlace((JSONObject) jsonArray.get(i));
               placesList.add(placeMap);
           }
           catch (JSONException e) {
               e.printStackTrace();
           }

       }
       return placesList;
   }

   public List<HashMap<String,String>> parse(String jsonData)
   {
       JSONArray jsonArray=null;
       JSONObject jsonObject;

       try {
           jsonObject=new JSONObject(jsonData);
           jsonArray=jsonObject.getJSONArray("results");
       }
       catch (JSONException e) {
           e.printStackTrace();
       }
       return getPlaces(jsonArray);

   }





}
