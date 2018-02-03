package com.example.songxixuan.xixuan_subbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by songxixuan on 2018/2/3.
 */

public class Subscription {
    private String name;
    private Date date;
    private double charge;
    private String comment;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public Subscription() {
        name = "";
        charge = 0;
        comment="";
        date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate(){
        return date;
    }
    public String getDateStr() {
        return FORMAT.format(date);
    }

    public void setDate(String date) {
        try {
            this.date = FORMAT.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return name;
    }
}
