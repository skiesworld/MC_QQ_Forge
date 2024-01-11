package com.github.theword.event;

import com.google.gson.annotations.SerializedName;

public class ForgeMessageEvent extends ForgeEvent {
    @SerializedName("message_id")
    private final String messageId = "";
    private final ForgeServerPlayer player;
    private final String message;

    public ForgeMessageEvent(String eventName, String subType, ForgeServerPlayer player, String message) {
        super(eventName, "message", subType);
        this.message = message;
        this.player = player;
    }
}
