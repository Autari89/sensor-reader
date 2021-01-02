package com.sensor.elements;

import android.content.Context;
import android.hardware.Sensor;

public class SensorComponent {
    private Sensor mSensor;
    private String mSensorName;
    private int mSensorPicture;

    public SensorComponent(Context context, Sensor sensor, String sensorName, int sensorPicture){
        this.mSensor = sensor;
        this.mSensorName = sensorName;
        this.mSensorPicture = sensorPicture;
    }

    public String getSensorName() {
        return mSensorName;
    }

    public void setSensorName(String mSensorName) {
        this.mSensorName = mSensorName;
    }

    public int getSensorPicture() {
        return mSensorPicture;
    }

    public void setSensorPicture(int mSensorPicture) {
        this.mSensorPicture = mSensorPicture;
    }
}
