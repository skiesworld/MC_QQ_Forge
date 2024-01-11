package com.github.theword.event;

public class ForgePlayerRespawnEvent extends ForgeMessageEvent {

    public ForgePlayerRespawnEvent(ForgeServerPlayer player, String message) {
        super("ForgePlayerRespawnEvent", "death", player, message);
    }
}
