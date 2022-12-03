package com.example.gorup16project;

public class jobs {

    private String title;
    private String pay;
    private String location;
    private String duration;
    private boolean urgent;

    public jobs() {
    }

    public jobs(String title, String pay, String location, String duration, boolean urgent) {
        this.title = title;
        this.duration = duration;
        this.location = location;
        this.urgent = urgent;
        this.pay = pay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
}
