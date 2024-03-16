package com.github.theword.models;

public final class ForgePlayerLoggedOutEvent extends ForgeNoticeEvent {
    public ForgePlayerLoggedOutEvent(ForgeServerPlayer player) {
        super("ForgePlayerLoggedOutEvent", "quit", player);
    }
}
