package com.example.flychicken.a4thtry;

/**
 * Created by flychicken on 2017/11/12.
 */

public class workInput {
    String title;
    String id;
    String lon;
    String lat;


    public workInput(String id, String title,String lat,String lon) {
        this.title = title;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

}
