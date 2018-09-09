package com.yadevapp.tutorial.recyclerview.entity;

public class Car {
    private final String TAG = getClass().getSimpleName();
    private String mName;
    private int mYear;
    private String mBrand;
    private String mEngine;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmEngine() {
        return mEngine;
    }

    public void setmEngine(String mEngine) {
        this.mEngine = mEngine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mName='" + mName + '\'' +
                ", mYear=" + mYear +
                ", mBrand='" + mBrand + '\'' +
                ", mEngine='" + mEngine + '\'' +
                '}';
    }
}
