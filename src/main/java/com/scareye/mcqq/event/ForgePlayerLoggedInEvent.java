package com.scareye.mcqq.event;

public class ForgePlayerLoggedInEvent extends ForgeNoticeEvent {
    private final String sub_type = "join";
    private ForgeServerPlayer player;

    public String getSub_type() {
        return sub_type;
    }


    public String getEvent_name() {
        return "ForgePlayerLoggedInEvent";
    }

    public ForgeServerPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ForgeServerPlayer player) {
        this.player = player;
    }

    public ForgePlayerLoggedInEvent(String server_name, ForgeServerPlayer player) {
        super("ForgePlayerLoggedInEvent", server_name);
        this.player = player;
    }
}
