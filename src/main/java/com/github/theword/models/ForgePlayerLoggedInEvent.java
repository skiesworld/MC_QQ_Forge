package com.github.theword.models;

public final class ForgePlayerLoggedInEvent extends ForgeNoticeEvent {
    public ForgePlayerLoggedInEvent(ForgeServerPlayer player) {
        super("ForgePlayerLoggedInEvent", "join", player);
    }
}
