package com.example.trainbooking;

public class seat_ {
    public  int num;
    public  float cos;
    public  String pos;
    public  String im;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getCos() {
        return cos;
    }

    public void setCos(float cos) {
        this.cos = cos;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public seat_(float cos, String pos, String im) {
        this.cos = cos;
        this.pos = pos;
        this.im = im;
    }

    public seat_(int num, float cos, String pos, String im) {
        this.num = num;
        this.cos = cos;
        this.pos = pos;
        this.im = im;
    }
}
