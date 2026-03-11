package com.stonefacesoft.pictogramslibrary.Classes;

import com.stonefacesoft.pictogramslibrary.JsonUtils.JSONObjectManager;

import org.json.JSONArray;
import org.json.JSONObject;

public class OTTAAProjectObjects {
    protected String name;
    protected String name_en;
    protected int id;
    protected String pictogram;
    protected String editedPictogram;
    protected String url;
    protected String push;
    protected JSONArray relationShip;
    protected int type;
    protected JSONArray hour,age,gender,location;
    protected JSONObject object;
    protected String locale="en";
    protected JSONObjectManager manager;

    public OTTAAProjectObjects(){
        manager=new JSONObjectManager();
    }

    public OTTAAProjectObjects(JSONObject object,String locale){
        manager=new JSONObjectManager();
        this.object=object;
        this.locale=locale;
        setUpPictogram();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setPictogram(String pictogram) {
        this.pictogram = pictogram;
    }

    public void setEditedPictogram(String editedPictogram) {
        this.editedPictogram = editedPictogram;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRelationShip(JSONArray relationShip) {
        this.relationShip = relationShip;
    }

    public void setAge(JSONArray age) {
        this.age = age;
    }

    public void setHour(JSONArray hour) {
        this.hour = hour;
    }

    public void setGender(JSONArray gender) {
        this.gender = gender;
    }

    public void setLocation(JSONArray location) {
        this.location = location;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void createObject(){

    }
    public  JSONObject toJsonObject(){
        return null;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public JSONArray getAge() {
        return age;
    }

    public JSONArray getHour() {
        return hour;
    }

    public JSONArray getGender() {
        return gender;
    }

    public JSONArray getLocation() {
        return location;
    }

    public String getLocale() {
        return locale;
    }

    public JSONArray getRelationShip() {
        return relationShip;
    }

    public JSONObject getObject() {
        return object;
    }

    public String getEditedPictogram() {
        return editedPictogram;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getName_en() {
        return name_en;
    }

    public String getPictogram() {
        return pictogram;
    }

    public String getPush() {
        return push;
    }

    public String getObjectName(){
        switch (locale){
            case "en":
                return name_en;
            default:
                return name;
        }
    }
    public void setUpPictogram(){
        JSONObject texto=manager.JsonObjectGetJSONObject(object,"texto","PictogramLibrary");
        this.name_en=manager.JsonObjectGetString(texto,"en","PictogramLibrary");
        this.name=manager.JsonObjectGetString(texto,locale,"PictogramLibrary");
        JSONObject picture=manager.JsonObjectGetJSONObject(object,"imagen","PictogramLibrary");
        this.pictogram=manager.JsonObjectGetString(picture,"picto","PictogramLibrary");
        this.editedPictogram=manager.JsonObjectGetString(picture,"pictoEditado","PictogramLibrary");
        this.url =manager.JsonObjectGetString(picture,"urlFoto","PictogramLibrary");
        this.push=manager.JsonObjectGetString(picture,"pushKey","PictogramLibrary");
        this.relationShip=manager.getJsonArray(object,"relacion","PictogramLibrary");
        this.hour=manager.getJsonArray(object,"hora","PictogramLibrary");
        this.age=manager.getJsonArray(object,"edad","PictogramLibrary");
        this.gender=manager.getJsonArray(object,"sexo","PictogramLibrary");
        this.location=manager.getJsonArray(object,"ubicacion","PictogramLibrary");
        this.type=manager.JsonObjectGetInt(object,"tipo","PictogramLibrary");
        this.id=manager.JsonObjectGetInt(object,"id","PictogramLibrary");
    }



    public void setLocale(String locale) {
        this.locale = locale;
    }

}
