package com.lecture.soapservice.entity;

public class Tree {
    private int id;
    private String fruit;
    private String geoLocation;

    public Tree(int id, String fruit, String geoLocation) {
        this.id = id;
        this.fruit = fruit;
        this.geoLocation = geoLocation;
    }

    public Tree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
}
