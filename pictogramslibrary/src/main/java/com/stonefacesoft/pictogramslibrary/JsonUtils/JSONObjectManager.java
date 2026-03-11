package com.stonefacesoft.pictogramslibrary.JsonUtils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * <Objective>
 *     Management the Json object files
 * </Objective>
 *
 * */
public class JSONObjectManager {


    public JSONObject createJsonObject(){
        JSONObject object=new JSONObject();
        return object;
    }

    public JSONArray createJsonArray(){
        JSONArray array=new JSONArray();
        return array;
    }
/**
 * Insert an string object
 * */
    public void JsonObjectloadString(JSONObject object,String name,String value,String className){
        try {
            object.put(name,value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
    }
    /**
     *
     *
     */
    public void JsonObjectloadInt(JSONObject object,String name,int value,String className){
        try {
            object.put(name,value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
    }
    /**
     *
     * */
    public void JsonObjectloadValue(JSONObject object,String name,boolean value,String className){
        try {
            object.put(name,value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
    }
    /**
     *
     * */
    public void JsonObjectloadObject(JSONObject object,String name,Object value,String className){
        try {
            object.put(name,value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
    }


 /**
  *
  * */
    public JSONObject JsonObjectGetJsonObject(JSONObject object,String name,Object value,String className){
        try {
          return  object.getJSONObject(name);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
        return createJsonObject();
    }
/**
 *
 * */
    public String JsonObjectGetString(JSONObject object,String value,String className){
        try {
           return object.getString(value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectGetString: "+e.getMessage() );
        }
        return "";
    }
    /**
     *
     * */
    public JSONObject JsonObjectGetJSONObject(JSONObject object,String value,String className){
        try {
          return   object.getJSONObject(value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectGetJSONObject: "+ e.getMessage() );
        }
        return null;
    }
/**
 *
 * */
    public int JsonObjectGetInt(JSONObject object,String value,String className){
        try {
            return object.getInt(value);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectGetString: "+e.getMessage() );
        }
        return -1;
    }

    /**
     *
     * */
    public JSONArray getJsonArray(JSONObject object, String name, String className){
        try {
            return object.getJSONArray(name);
        } catch (JSONException e) {
            Log.d(className, "JsonObjectloadString: "+ e.getMessage() );
        }
        return createJsonArray();
    }





}
