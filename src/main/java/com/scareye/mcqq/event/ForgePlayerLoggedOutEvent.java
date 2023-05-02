package com.scareye.mcqq.event;

public class ForgePlayerLoggedOutEvent extends ForgeNoticeEvent {
    private final String sub_type = "quit";
    private ForgeServerPlayer player;

    public String getSub_type() {
        return sub_type;
    }


    public String getEvent_name() {
        return "PlayerLoggedOutEvent";
    }

    public ForgeServerPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ForgeServerPlayer player) {
        this.player = player;
    }

    public ForgePlayerLoggedOutEvent(String server_name, ForgeServerPlayer player) {
        super("ForgePlayerLoggedOutEvent", server_name);
        this.player = player;
    }
}
