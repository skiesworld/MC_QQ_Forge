package com.github.theword.event;

public class ForgePlayerRespawnEvent extends ForgeMessageEvent {

    public ForgePlayerRespawnEvent(String messageId, ForgeServerPlayer player, String message) {
        super("ForgePlayerRespawnEvent", "death", messageId, player, message);
    }
}
