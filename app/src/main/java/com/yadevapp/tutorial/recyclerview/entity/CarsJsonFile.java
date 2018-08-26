package com.yadevapp.tutorial.recyclerview.entity;

import java.util.ArrayList;

public class CarsJsonFile {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<Car> carArray;

    public ArrayList<Car> getCarArray() {
        return carArray;
    }

    public void setCarArray(ArrayList<Car> carArray) {
        this.carArray = carArray;
    }
}
