package com.example.multimediainformationdisplayapplication;

public class Car {
    private String name;
    private int price;
    private int horsepower;
    private double zeroToSixty;
    private int topSpeed;
    private int mpg;
    private int image;

    public Car(String n, int p, int h, double zTS, int tS, int m, int i){
        name = n;
        price = p;
        horsepower = h;
        zeroToSixty = zTS;
        topSpeed = tS;
        mpg = m;
        image = i;
    }

    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getHorsepower(){
        return horsepower;
    }
    public double getZeroToSixty(){
        return zeroToSixty;
    }
    public int getTopSpeed(){
        return topSpeed;
    }
    public int getMPG(){
        return mpg;
    }
    public int getImage() { return image; }
}