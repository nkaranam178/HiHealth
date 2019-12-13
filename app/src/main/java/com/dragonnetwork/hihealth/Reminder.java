package com.dragonnetwork.hihealth.data;

import com.google.firebase.Timestamp;

public class Reminder {
    private String Info;
    private String Instructions;
    private int Time; // 0 - morning 1 - noon 2 - night
    private int Type;

    public Reminder(String info, String instructions, int time, int type) {
        Info = info;
        Instructions = instructions;
        Time = time;
        Type = type;
    }
    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
