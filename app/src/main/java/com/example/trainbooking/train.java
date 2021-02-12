package com.example.trainbooking;

public class train {
    int num;
    String hour;
    String distin;
    String image;

    public train(String hour, String distin, String image) {
        this.hour = hour;
        this.distin = distin;
        this.image = image;
    }

    public train(int num, String hour, String distin, String image) {
        this.num = num;
        this.hour = hour;
        this.distin = distin;
        this.image = image;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDistin() {
        return distin;
    }

    public void setDistin(String distin) {
        this.distin = distin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
