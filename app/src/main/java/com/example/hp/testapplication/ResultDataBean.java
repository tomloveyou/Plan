package com.example.hp.testapplication;

import com.google.gson.annotations.SerializedName;

/**
 * @author yl
 * @version 1.0
 * @date 2017/12/6 11:12
 */

public class ResultDataBean {
    /**
     * isWarn : false
     * trackData : {"airspeed":0,"arcaddr":"094C6C","callSign":"001ⅠA0D09388","climbRate":0,"distanceX":-1,"distanceY":-1,"headingDegeree":-1,"height":2,"lat":30.893705500082056,"lon":103.7887107549416,"speed":0,"ssr":"","time":1512525895000,"trackNum":609388,"uavFlag":1}
     * warn : none
     */
    @SerializedName("isWarn")
    private boolean isWarn;
    @SerializedName("trackData")
    private TrackDataBean trackData;
    @SerializedName("warn")
    private String warn;

    public boolean isIsWarn() {
        return isWarn;
    }

    public void setIsWarn(boolean isWarn) {
        this.isWarn = isWarn;
    }

    public TrackDataBean getTrackData() {
        return trackData;
    }

    public void setTrackData(TrackDataBean trackData) {
        this.trackData = trackData;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public static class TrackDataBean {
        /**
         * airspeed : 0
         * arcaddr : 094C6C
         * callSign : 001ⅠA0D09388
         * climbRate : 0
         * distanceX : -1
         * distanceY : -1
         * headingDegeree : -1
         * height : 2
         * lat : 30.893705500082056
         * lon : 103.7887107549416
         * speed : 0
         * ssr :
         * time : 1512525895000
         * trackNum : 609388
         * uavFlag : 1
         */
        @SerializedName("airspeed")
        private int airspeed;
        @SerializedName("arcaddr")
        private String arcaddr;
        @SerializedName("callSign")
        private String callSign;
        @SerializedName("climbRate")
        private int climbRate;
        @SerializedName("distanceX")
        private int distanceX;
        @SerializedName("distanceY")
        private int distanceY;
        @SerializedName("headingDegeree")
        private int headingDegeree;
        @SerializedName("height")
        private int height;
        @SerializedName("lat")
        private double lat;
        @SerializedName("lon")
        private double lon;
        @SerializedName("speed")
        private int speed;
        @SerializedName("ssr")
        private String ssr;
        @SerializedName("time")
        private long time;
        @SerializedName("trackNum")
        private int trackNum;
        @SerializedName("uavFlag")
        private int uavFlag;

        public int getAirspeed() {
            return airspeed;
        }

        public void setAirspeed(int airspeed) {
            this.airspeed = airspeed;
        }

        public String getArcaddr() {
            return arcaddr;
        }

        public void setArcaddr(String arcaddr) {
            this.arcaddr = arcaddr;
        }

        public String getCallSign() {
            return callSign;
        }

        public void setCallSign(String callSign) {
            this.callSign = callSign;
        }

        public int getClimbRate() {
            return climbRate;
        }

        public void setClimbRate(int climbRate) {
            this.climbRate = climbRate;
        }

        public int getDistanceX() {
            return distanceX;
        }

        public void setDistanceX(int distanceX) {
            this.distanceX = distanceX;
        }

        public int getDistanceY() {
            return distanceY;
        }

        public void setDistanceY(int distanceY) {
            this.distanceY = distanceY;
        }

        public int getHeadingDegeree() {
            return headingDegeree;
        }

        public void setHeadingDegeree(int headingDegeree) {
            this.headingDegeree = headingDegeree;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getSsr() {
            return ssr;
        }

        public void setSsr(String ssr) {
            this.ssr = ssr;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTrackNum() {
            return trackNum;
        }

        public void setTrackNum(int trackNum) {
            this.trackNum = trackNum;
        }

        public int getUavFlag() {
            return uavFlag;
        }

        public void setUavFlag(int uavFlag) {
            this.uavFlag = uavFlag;
        }
    }
}
