package com.zhang;

import java.util.Date;

public class Rip {

    int net;
    int distance;
    String router;
    Date time;
    boolean isValid;

    public Rip() {
        this.isValid=true;
    }

    public Rip( int net, int distance, String router) {

        this.net = net;
        this.distance = distance;
        this.router = router;
        this.isValid=true;
    }

    @Override
    public String toString() {
        return "Rip{" +
                "net=" + net +
                ", distance=" + distance +
                ", router='" + router + '\'' +
                // ", time=" + time +
                // ", isValid=" + isValid +
                '}';
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


}
