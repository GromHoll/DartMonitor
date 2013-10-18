package edu.nntu.dart.entity;

import java.util.Date;

public class WavePoint {
    private Date date;
    private double height;

    public WavePoint(Date date, double height) {
        this.date = (date != null) ? date : new Date();
        this.height = height;
    }

    public Date getDate() {
        return date;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof WavePoint)) return false;

        WavePoint wavePoint = (WavePoint) obj;
        return this.date.equals(wavePoint.date);
    }

}
