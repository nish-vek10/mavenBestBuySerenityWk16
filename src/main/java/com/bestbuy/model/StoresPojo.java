package com.bestbuy.model;

public class StoresPojo {

    private String name;
    private String type;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private int lat;
    private int lng;
    private String hours;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress2(String address2){
        this.address2 = address2;
    }

    public String getAddress2(){
        return this.address2;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return this.city;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }

    public void setZip(String zip){
        this.zip = zip;
    }

    public String getZip(){
        return this.zip;
    }

    public void setLat(int lat){
        this.lat = lat;
    }

    public int getLat(){
        return this.lat;
    }

    public void setLng(int lng){
        this.lng = lng;
    }

    public int getLng(){
        return this.lng;
    }

    public void setHours(String hours){
        this.hours = hours;
    }

    public String getHours(){
        return this.hours;
    }
}
