package com.scareye.mcqq.event;


public class ForgeServerChatEvent {
    private final String post_type = "message";
    private final String sub_type = "chat";
    private final String event_name = "ForgeServerChatEvent";
    private String server_name;
    private String message;
    private ForgeServerPlayer player;

    public String getPost_type() {
        return post_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForgeServerPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ForgeServerPlayer player) {
        this.player = player;
    }

    public ForgeServerChatEvent() {
    }

    public ForgeServerChatEvent(String server_name, String message, ForgeServerPlayer player) {
        this.server_name = server_name;
        this.message = message;
        this.player = player;
    }
}
