package com.myapp.battleofcards;

public class Card {
    private String name;
    private int maxSpeed;
    private float acceleration;
    private int horsePower;
    // private Engine engine;

    public Card(String name, int maxSpeed, float acceleration, int horsePower) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.horsePower = horsePower;
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

}
