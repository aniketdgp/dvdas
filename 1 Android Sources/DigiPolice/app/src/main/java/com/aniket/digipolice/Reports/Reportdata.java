package com.aniket.digipolice.Reports;

public class Reportdata {

    String Reason,Date,Time,Location,Username;

    public Reportdata(String reason, String date, String time, String location,String username) {
        Reason = reason;
        Date = date;
        Time = time;
        Location = location;
        Username = username;
    }

    public Reportdata() {

    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
