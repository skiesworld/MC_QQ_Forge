package com.scareye.mcqq.event;

public class ForgeNoticeEvent extends ForgeEvent {
    private final String post_type = "notice";

    public String getPost_type() {
        return post_type;
    }

    public ForgeNoticeEvent(String event_name, String server_name) {
        super(event_name, server_name);
    }

}
