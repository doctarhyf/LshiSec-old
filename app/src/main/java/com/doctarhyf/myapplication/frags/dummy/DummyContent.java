package com.doctarhyf.myapplication.frags.dummy;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<InsecSignal> ITEMS = new ArrayList<InsecSignal>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, InsecSignal> ITEM_MAP = new HashMap<String, InsecSignal>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(InsecSignal insecSignal) {
        ITEMS.add(insecSignal);
        ITEM_MAP.put(insecSignal.getUID(), insecSignal);
    }

    private static InsecSignal createDummyItem(int position) {

        Random random = new Random();

        return new InsecSignal(new Random().nextInt(10000) + "", random.nextFloat() , random.nextFloat(), "send_uid", 1500000);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class InsecSignal {
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
    }
}
