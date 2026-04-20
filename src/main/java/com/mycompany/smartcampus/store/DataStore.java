package com.mycompany.smartcampus.store;

import com.mycompany.smartcampus.model.Room;
import com.mycompany.smartcampus.model.Sensor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.mycompany.smartcampus.model.SensorReading;
import java.util.List;
import java.util.ArrayList;

public class DataStore {
    public static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static final Map<String, Sensor> sensors = new ConcurrentHashMap<>();

    static {
        rooms.put("LIB-301", new Room("LIB-301", "Library Quiet Study", 40));
        rooms.put("LAB-101", new Room("LAB-101", "Computer Lab 101", 30));
    }

    private DataStore() {
    }
    public static final Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();
}