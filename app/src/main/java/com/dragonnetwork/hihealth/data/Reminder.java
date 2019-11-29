package com.dragonnetwork.hihealth.data;

import com.google.firebase.Timestamp;

public class Reminder {
    private String Info;
    private String Instructions;
    private Timestamp time;

    public Reminder(String info, String instructions, Timestamp time) {
        Info = info;
        Instructions = instructions;
        this.time = time;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
