package com.example.trainbooking;

public class person {
    int num;
    String name;
    int seat_num;
    int train_num;
    String address;

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

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public int getTrain_num() {
        return train_num;
    }

    public void setTrain_num(int train_num) {
        this.train_num = train_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public person(String name, int seat_num, int train_num, String address) {
        this.name = name;
        this.seat_num = seat_num;
        this.train_num = train_num;
        this.address = address;
    }

    public person(int num, String name, int seat_num, int train_num, String address) {
        this.num = num;
        this.name = name;
        this.seat_num = seat_num;
        this.train_num = train_num;
        this.address = address;
    }
}
