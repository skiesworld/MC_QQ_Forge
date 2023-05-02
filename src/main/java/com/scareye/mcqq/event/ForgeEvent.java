package com.scareye.mcqq.event;

public class ForgeEvent {
    private String server_name;
    private String event_name;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }



    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public ForgeEvent(String event_name, String server_name) {
        this.event_name = event_name;
        this.server_name = server_name;
    }
}
