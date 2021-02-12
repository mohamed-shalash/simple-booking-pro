package com.example.trainbooking;

public class remaking_per {
    int num;
    String name;
    String address;
    String seat;
    String hour;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public remaking_per(String name, String address, String seat, String hour) {
        this.name = name;
        this.address = address;
        this.seat = seat;
        this.hour = hour;
    }

    public remaking_per(int num, String name,String seat, String hour, String address) {
        this.num = num;
        this.name = name;
        this.address = address;
        this.seat = seat;
        this.hour = hour;
    }
}
