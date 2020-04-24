package com.doctarhyf.myapplication;

import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

public class InsecSignal {
    public static final String KEY_INSEC_SIGNAL = "keyInsecItem";
    private String UID;
    private float lat;
    private float lon;
    private String senderUID;
    private long timestamp;

    public InsecSignal() {

    }

    public InsecSignal(String _UID, float _lat, float _lon, String _senderUID, long _timestamp){
        this.UID = _UID;
        this.lat = _lat;
        this.lon = _lon;
        this.senderUID = _senderUID;
        this.timestamp = _timestamp;
    }

    @Override
    public String toString() {
        return "INSEC SIGNAL : \n==============\n" +
                "UID : " + UID + "\n" +
                "lat : " + lat + "\n" +
                "lon : " + lon + "\n" +
                "senderUID : " + senderUID + "\n" +
                "timestamp : " + timestamp + "\n";

    }

    public LatLng latLng(){
        return new LatLng(lat, lon);
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getSenderUID() {
        return senderUID;
    }

    public void setSenderUID(String senderUID) {
        this.senderUID = senderUID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }
}